package com.xinerji.xinerjidc.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xinerji.xinerjidc.R;
import com.xinerji.xinerjidc.adapters.NetDateTimeJsonAdapter;
import com.xinerji.xinerjidc.adapters.TripOrderListAdapter;
import com.xinerji.xinerjidc.model.CustomerSharedData;
import com.xinerji.xinerjidc.model.GetTripByTruckIdResponse;
import com.xinerji.xinerjidc.model.OrderDetail;
import com.xinerji.xinerjidc.model.TripOrder;
import com.xinerji.xinerjidc.utilities.ActionBarUtil;
import com.xinerji.xinerjidc.utilities.JsonServiceCallingUtil;
import com.xinerji.xinerjidc.utilities.PopupUtil;
import com.xinerji.xinerjidc.utilities.SessionUtil;
import com.xinerji.xinerjidc.utilities.SharedPreferancesUtil;

import java.util.Date;

public class TripActivity extends Activity {

    JsonServiceCallingUtil jsonServiceCallingUtil;
    final PopupUtil popupUtil = new PopupUtil();
    Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new NetDateTimeJsonAdapter()).create();

    CustomerSharedData customerShareData;


    ActionBarUtil actionBarUtil;

    GetTripByTruckIdResponse getTripByTruckIdResponse;


    TextView tv_trip_name;
    TextView tv_trip_detail;
    ListView trip_lv_order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip);

        tv_trip_name = findViewById(R.id.trip_tv_trip_name);
        tv_trip_detail = findViewById(R.id.trip_tv_trip_detail);
        trip_lv_order = findViewById(R.id.trip_lv_order);

        customerShareData = SharedPreferancesUtil.GetCustomerSharedData(this);


        actionBarUtil = new ActionBarUtil(this,  getResources().getString(R.string.trip_activity_title), DashboardActivity.class);

        getTripByTruckIdResponse = SessionUtil.getGetTripByTruckIdResponse();

        tv_trip_name.setText(getTripByTruckIdResponse.getTrip().getName());
        tv_trip_detail.setText(customerShareData.getPlaque() + "/" + getTripByTruckIdResponse.getTrip().getCompany());


        trip_lv_order.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TripOrder selectedValue = (TripOrder) getTripByTruckIdResponse.getTripOrderList().get(i);

                SessionUtil.setSelectedTripOrder(selectedValue);

                //Toast.makeText(TripActivity.this, selectedValue.getCompanyName(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(TripActivity.this, FragmentActivity.class);

                startActivity(intent);


            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        TripOrderListAdapter orderListAdapter =
                new TripOrderListAdapter(this, R.layout.listview_orderlist, getTripByTruckIdResponse.getTripOrderList());

        if(trip_lv_order!=null){
            trip_lv_order.setAdapter(orderListAdapter);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        TripOrderListAdapter orderListAdapter =
                new TripOrderListAdapter(this, R.layout.listview_orderlist, getTripByTruckIdResponse.getTripOrderList());

        if(trip_lv_order!=null){
            trip_lv_order.setAdapter(orderListAdapter);
        }
    }
}
