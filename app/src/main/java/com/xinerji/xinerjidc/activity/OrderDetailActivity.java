package com.xinerji.xinerjidc.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xinerji.xinerjidc.R;
import com.xinerji.xinerjidc.adapters.NetDateTimeJsonAdapter;
import com.xinerji.xinerjidc.adapters.OrderDetailAdapter;
import com.xinerji.xinerjidc.model.CustomerSharedData;
import com.xinerji.xinerjidc.model.Order;
import com.xinerji.xinerjidc.model.OrderDetailRow;
import com.xinerji.xinerjidc.model.OrderRepresenter;
import com.xinerji.xinerjidc.model.TripOrder;
import com.xinerji.xinerjidc.utilities.ActionBarUtil;
import com.xinerji.xinerjidc.utilities.JsonServiceCallingUtil;
import com.xinerji.xinerjidc.utilities.PopupUtil;
import com.xinerji.xinerjidc.utilities.SessionUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDetailActivity extends Activity {

    JsonServiceCallingUtil jsonServiceCallingUtil;
    final PopupUtil popupUtil = new PopupUtil();
    Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new NetDateTimeJsonAdapter()).create();

    CustomerSharedData customerShareData;

    ActionBarUtil actionBarUtil;

    TripOrder selectedTripOrder;

    TextView tv_company_name;
    TextView tv_branch_name;

    private RecyclerView recyclerView;
    private List<OrderDetailRow> orderDetailRowList = new ArrayList<>();
    private OrderDetailAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        tv_company_name = findViewById(R.id.order_detail_company_name);
        tv_branch_name = findViewById(R.id.order_detail_branch_name);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        actionBarUtil = new ActionBarUtil(this,  getResources().getString(R.string.trip_activity_title), DashboardActivity.class);

        selectedTripOrder = SessionUtil.getSelectedTripOrder();

        tv_company_name.setText(selectedTripOrder.getCompanyName());
        tv_branch_name.setText(selectedTripOrder.getBranchName());

        mAdapter = new OrderDetailAdapter(orderDetailRowList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        prepareOrderDetailRowData();

    }


    private void prepareOrderDetailRowData() {
        OrderDetailRow orderDetailRow = new OrderDetailRow("Sipariş Kodu", selectedTripOrder.getTitle(),"");
        orderDetailRowList.add(orderDetailRow);

        orderDetailRow = new OrderDetailRow("İrsaliye No", selectedTripOrder.getConsignmentNo(),"");
        orderDetailRowList.add(orderDetailRow);

        orderDetailRow = new OrderDetailRow("Fatura No", selectedTripOrder.getReceiptNo(),"");
        orderDetailRowList.add(orderDetailRow);

        orderDetailRow = new OrderDetailRow("Adres", selectedTripOrder.getAdress(),"");
        orderDetailRowList.add(orderDetailRow);

        orderDetailRow = new OrderDetailRow("Adres", selectedTripOrder.getAdress(),"");
        orderDetailRowList.add(orderDetailRow);

        orderDetailRow = new OrderDetailRow("Adres", selectedTripOrder.getAdress(),"");
        orderDetailRowList.add(orderDetailRow);

        orderDetailRow = new OrderDetailRow("Adres", selectedTripOrder.getAdress(),"");
        orderDetailRowList.add(orderDetailRow);

        orderDetailRow = new OrderDetailRow("Adres", selectedTripOrder.getAdress(),"");
        orderDetailRowList.add(orderDetailRow);

        orderDetailRow = new OrderDetailRow("Adres", selectedTripOrder.getAdress(),"");
        orderDetailRowList.add(orderDetailRow);

        orderDetailRow = new OrderDetailRow("Adres", selectedTripOrder.getAdress(),"");
        orderDetailRowList.add(orderDetailRow);

        orderDetailRow = new OrderDetailRow("Adres", selectedTripOrder.getAdress(),"");
        orderDetailRowList.add(orderDetailRow);


        List<OrderRepresenter> orderRepresenterList =selectedTripOrder.getOrderRepresenterList();

        for(int i=0;i<orderRepresenterList.size();i++){
            orderDetailRow = new OrderDetailRow(orderRepresenterList.get(i).getType(), orderRepresenterList.get(i).getName(),orderRepresenterList.get(i).getPhone());
            orderDetailRowList.add(orderDetailRow);
        }

        mAdapter.notifyDataSetChanged();
    }
}
