package com.xinerji.xinerjidc.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xinerji.xinerjidc.R;
import com.xinerji.xinerjidc.model.OrderDetail;
import com.xinerji.xinerjidc.model.OrderDetailRow;
import com.xinerji.xinerjidc.model.TripOrder;

import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.MyViewHolder> {


    private List<OrderDetail> orderDetailList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView value1, value2;


        public MyViewHolder(View view) {
            super(view);
            value1 = (TextView) view.findViewById(R.id.product_list_tv_value);
            value2 = (TextView) view.findViewById(R.id.product_list_tv_value2);
        }
    }

    public ProductListAdapter(List<OrderDetail> orderDetailList){
        this.orderDetailList = orderDetailList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final OrderDetail orderDetail = orderDetailList.get(position);
        holder.value1.setText(orderDetail.getProductDescription());
        holder.value2.setText(Integer.toString(orderDetail.getQuantity()));

        if(position %2 == 1)
        {
            holder.itemView.setBackgroundColor(Color.parseColor("#effaff"));
            // Set a background color for ListView regular row/item

        }
        else
        {
            holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
            // Set the background color for alternate row/item
        }


    }

    @Override
    public int getItemCount() {
        return orderDetailList.size();
    }

}