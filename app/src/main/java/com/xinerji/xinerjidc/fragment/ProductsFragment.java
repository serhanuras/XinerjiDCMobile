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
import com.xinerji.xinerjidc.adapters.ProductListAdapter;
import com.xinerji.xinerjidc.model.OrderDetail;
import com.xinerji.xinerjidc.model.OrderDetailRow;
import com.xinerji.xinerjidc.model.OrderRepresenter;
import com.xinerji.xinerjidc.model.TripOrder;
import com.xinerji.xinerjidc.utilities.SessionUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Belal on 1/23/2018.
 */

public class ProductsFragment extends Fragment {
    TripOrder selectedTripOrder;

    private RecyclerView recyclerView;
    private List<OrderDetail> orderDetailList = SessionUtil.getSelectedTripOrder().getOrderDetailList();
    private ProductListAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //just change the fragment_dashboard
        //with the fragment you want to inflate
        //like if the class is HomeFragment it should have R.layout.home_fragment
        //if it is DashboardFragment it should have R.layout.fragment_dashboard
        View view = inflater.inflate(R.layout.fragment_products, null);

        recyclerView = (RecyclerView) view.findViewById(R.id.fragment_products_recycler_view);

        mAdapter = new ProductListAdapter(orderDetailList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();

        return view;
    }

}