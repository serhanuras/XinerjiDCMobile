package com.xinerji.xinerjidc.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xinerji.xinerjidc.R;
import com.xinerji.xinerjidc.model.OrderDetail;
import com.xinerji.xinerjidc.model.OrderDetailRow;

import java.util.List;

public class OrderDetailAdapter extends RecyclerView.Adapter<OrderDetailAdapter.MyViewHolder> {


    private List<OrderDetailRow> orderDetailRowList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, value;
        public ImageView phone;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.order_detail_list_tv_title);
            value = (TextView) view.findViewById(R.id.order_detail_list_tv_value);
            phone = (ImageView) view.findViewById(R.id.order_detail_iv_phone);
        }
    }

    public OrderDetailAdapter(List<OrderDetailRow> orderDetailRowList){
        this.orderDetailRowList = orderDetailRowList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_detail_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final OrderDetailRow orderDetailRow = orderDetailRowList.get(position);
        holder.title.setText(orderDetailRow.getTitle());
        holder.value.setText(orderDetailRow.getValue());

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

        if(orderDetailRow.getPhone()!=""){
            holder.phone.setVisibility(View.VISIBLE);

            holder.phone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();

                    String phone_number = orderDetailRow.getPhone().replace("(","").replace(")","").replace("-"
                    ,"").replace(" ","");

                    phone_number = "tel:" + phone_number;

                    Toast.makeText(context, phone_number,
                            Toast.LENGTH_LONG).show();

                    Intent i=new Intent(Intent.ACTION_DIAL, Uri.parse(phone_number));
                    context.startActivity(i);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return orderDetailRowList.size();
    }

}
