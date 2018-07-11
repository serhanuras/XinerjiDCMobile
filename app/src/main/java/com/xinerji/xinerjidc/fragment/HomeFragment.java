package com.xinerji.xinerjidc.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xinerji.xinerjidc.R;
import com.xinerji.xinerjidc.adapters.OrderDetailAdapter;
import com.xinerji.xinerjidc.model.OrderDetailRow;
import com.xinerji.xinerjidc.model.OrderRepresenter;
import com.xinerji.xinerjidc.model.TripOrder;
import com.xinerji.xinerjidc.utilities.SessionUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Belal on 1/23/2018.
 */

public class HomeFragment extends Fragment {

    TripOrder selectedTripOrder;

    private RecyclerView recyclerView;
    private List<OrderDetailRow> orderDetailRowList = new ArrayList<>();
    private OrderDetailAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //just change the fragment_dashboard
        //with the fragment you want to inflate
        //like if the class is HomeFragment it should have R.layout.home_fragment
        //if it is DashboardFragment it should have R.layout.fragment_dashboard
        View view = inflater.inflate(R.layout.fragment_home, null);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        selectedTripOrder = SessionUtil.getSelectedTripOrder();

        mAdapter = new OrderDetailAdapter(orderDetailRowList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        prepareOrderDetailRowData();

        return view;
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


        orderDetailRowList.add(orderDetailRow);


        List<OrderRepresenter> orderRepresenterList =selectedTripOrder.getOrderRepresenterList();

        for(int i=0;i<orderRepresenterList.size();i++){
            orderDetailRow = new OrderDetailRow(orderRepresenterList.get(i).getType(), orderRepresenterList.get(i).getName(),orderRepresenterList.get(i).getPhone());
            orderDetailRowList.add(orderDetailRow);
        }

        mAdapter.notifyDataSetChanged();
    }
}