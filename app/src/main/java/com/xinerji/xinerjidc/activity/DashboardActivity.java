package com.xinerji.xinerjidc.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xinerji.xinerjidc.R;
import com.xinerji.xinerjidc.adapters.NetDateTimeJsonAdapter;
import com.xinerji.xinerjidc.model.CustomerSharedData;
import com.xinerji.xinerjidc.model.GetTripByTruckIdRequest;
import com.xinerji.xinerjidc.model.GetTripByTruckIdResponse;
import com.xinerji.xinerjidc.model.ValidateMobileLogonRequest;
import com.xinerji.xinerjidc.model.ValidateMobileLogonResponse;
import com.xinerji.xinerjidc.utilities.JsonServiceCallingUtil;
import com.xinerji.xinerjidc.utilities.PopupUtil;
import com.xinerji.xinerjidc.utilities.SessionUtil;
import com.xinerji.xinerjidc.utilities.SharedPreferancesUtil;
import com.xinerji.xinerjidc.utilities.XinerjiServiceUtil;

import org.json.JSONObject;

import java.util.Date;

import okhttp3.Request;
import okhttp3.Response;

public class DashboardActivity extends Activity {

    JsonServiceCallingUtil jsonServiceCallingUtil;
    final PopupUtil popupUtil = new PopupUtil();
    Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new NetDateTimeJsonAdapter()).create();


    TextView tv_member_name;
    RelativeLayout rl_order_approval;

    CustomerSharedData customerShareData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        tv_member_name = findViewById(R.id.dashboard_tv_member_name);
        rl_order_approval = findViewById(R.id.dashboard_rl_order_approval);

        customerShareData = SharedPreferancesUtil.GetCustomerSharedData(this);

        rl_order_approval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                jsonServiceCallingUtil = new JsonServiceCallingUtil(DashboardActivity.this);

                jsonServiceCallingUtil.mDataLoadEvents = new JsonServiceCallingUtil.DataLoadEvents() {

                    @Override
                    public void onLoadingDataFinished() {

                        Response response = jsonServiceCallingUtil.getResponse();

                        popupUtil.dismissShowLoadingPopUp();

                        if (response.code() == JsonServiceCallingUtil.RESPONSE_CODE_SUCCESS) {


                            JSONObject json = jsonServiceCallingUtil.getResponseJsonObject();

                            GetTripByTruckIdResponse getTripByTruckIdResponse =
                                    gson.fromJson(json.toString(), GetTripByTruckIdResponse.class);

                            if (getTripByTruckIdResponse.getHeader().getError().getErrorCode() ==
                                    com.xinerji.xinerjidc.model.Error.SUCCEED) {

                                SessionUtil.setGetTripByTruckIdResponse(getTripByTruckIdResponse);

                                Intent intent = new Intent(DashboardActivity.this, TripActivity.class);

                                startActivity(intent);

                            }
                            else {
                                popupUtil.showServiceErrorPopUp(DashboardActivity.this, getTripByTruckIdResponse.getHeader().getError());
                            }
                        }
                        else {
                            popupUtil.showServiceCallException(DashboardActivity.this);
                        }
                    }
                };

                GetTripByTruckIdRequest getTripByTruckIdRequest = new GetTripByTruckIdRequest();
                getTripByTruckIdRequest.setTruckId(customerShareData.getId());


                Request request = XinerjiServiceUtil.getPostRequest(
                        XinerjiServiceUtil.TRIP_GETTRIPBYTRUCKID,
                        getTripByTruckIdRequest,
                        DashboardActivity.this);

                popupUtil.showLoadingPopUp(DashboardActivity.this);

                jsonServiceCallingUtil.LoadData(request);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();


        tv_member_name.setText(getResources().getString(R.string.dashboard_member_name) + customerShareData.getCustomerName());
    }
}
