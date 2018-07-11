package com.xinerji.xinerjidc.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xinerji.xinerjidc.R;
import com.xinerji.xinerjidc.adapters.NetDateTimeJsonAdapter;
import com.xinerji.xinerjidc.model.CustomerSharedData;
import com.xinerji.xinerjidc.model.ValidateMobileLogonRequest;
import com.xinerji.xinerjidc.model.ValidateMobileLogonResponse;
import com.xinerji.xinerjidc.utilities.JsonServiceCallingUtil;
import com.xinerji.xinerjidc.utilities.PopupUtil;
import com.xinerji.xinerjidc.utilities.SessionUtil;
import com.xinerji.xinerjidc.utilities.SharedPreferancesUtil;
import com.xinerji.xinerjidc.utilities.UtilMethods;
import com.xinerji.xinerjidc.utilities.XinerjiServiceUtil;

import org.json.JSONObject;

import java.util.Date;

import okhttp3.Request;
import okhttp3.Response;

public class LogonActivity extends Activity {

    JsonServiceCallingUtil jsonServiceCallingUtil;
    final PopupUtil popupUtil = new PopupUtil();
    Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new NetDateTimeJsonAdapter()).create();

    private CustomerSharedData customerSharedData;

    EditText et_plaque;
    EditText et_tckn;
    Button btn_logon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logon);

        et_plaque = findViewById(R.id.logon_et_plaque);
        et_tckn = findViewById(R.id.logon_et_tckn);
        btn_logon = findViewById(R.id.logon_btn_logon);


        customerSharedData = SharedPreferancesUtil.GetCustomerSharedData(this);

        et_plaque.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    UtilMethods.hideKeyboard(v, LogonActivity.this);
                }
            }
        });

        et_tckn.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    UtilMethods.hideKeyboard(v, LogonActivity.this);
                }
            }
        });

        btn_logon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et_plaque.getText().toString().equals("") || et_tckn.getText().toString().equals("")){
                    popupUtil.showErrorPopUp(LogonActivity.this, getResources().getString(R.string.logon_error_popup));
                }
                else{
                    jsonServiceCallingUtil = new JsonServiceCallingUtil(LogonActivity.this);

                    jsonServiceCallingUtil.mDataLoadEvents = new JsonServiceCallingUtil.DataLoadEvents() {

                        @Override
                        public void onLoadingDataFinished() {

                            Response response = jsonServiceCallingUtil.getResponse();

                            popupUtil.dismissShowLoadingPopUp();

                            if (response.code() == JsonServiceCallingUtil.RESPONSE_CODE_SUCCESS) {


                                JSONObject json = jsonServiceCallingUtil.getResponseJsonObject();

                                ValidateMobileLogonResponse validateMobileLogonResponse =
                                        gson.fromJson(json.toString(), ValidateMobileLogonResponse.class);

                                if (validateMobileLogonResponse.getHeader().getError().getErrorCode() ==
                                        com.xinerji.xinerjidc.model.Error.SUCCEED) {

                                    customerSharedData.setTcIdentifier(et_tckn.getText().toString());
                                    customerSharedData.setPlaque(et_plaque.getText().toString());
                                    customerSharedData.setCustomerName(validateMobileLogonResponse.getName());
                                    customerSharedData.setId(validateMobileLogonResponse.getTruckId());
                                    customerSharedData.setSessionToken(validateMobileLogonResponse.getSessionNumber());

                                    SharedPreferancesUtil.SetCustomerSharedData(LogonActivity.this, customerSharedData);




                                    Intent intent = new Intent(LogonActivity.this, DashboardActivity.class);

                                    //intent.putExtra("otpId", validateLogonResponse.getOtpId());

                                    startActivity(intent);
                                }
                                else {
                                    popupUtil.showServiceErrorPopUp(LogonActivity.this, validateMobileLogonResponse.getHeader().getError());
                                }
                            }
                            else {
                                popupUtil.showServiceCallException(LogonActivity.this);
                            }
                        }
                    };

                    ValidateMobileLogonRequest validateMobileLogonRequest = new ValidateMobileLogonRequest();
                    validateMobileLogonRequest.setPlaque(et_plaque.getText().toString());
                    validateMobileLogonRequest.setTCIdentifier(et_tckn.getText().toString());


                    Request request = XinerjiServiceUtil.getPostRequest(
                            XinerjiServiceUtil.AUTHENTICATION_VALIDATEMOBILELOGON,
                            validateMobileLogonRequest,
                            LogonActivity.this);

                    popupUtil.showLoadingPopUp(LogonActivity.this);

                    jsonServiceCallingUtil.LoadData(request);

                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();



        if(!customerSharedData.getPlaque().toString().equals("")){

            et_plaque.setText(customerSharedData.getPlaque().toString());
            et_tckn.setText(customerSharedData.getTcIdentifier().toString());
        }
        else{
            et_plaque.setText("34CCI11001");
            et_tckn.setText("TC00011001");
        }
    }
}
