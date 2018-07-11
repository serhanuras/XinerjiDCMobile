package com.xinerji.xinerjidc.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xinerji.xinerjidc.R;
import com.xinerji.xinerjidc.model.Order;
import com.xinerji.xinerjidc.model.TripOrder;

import java.util.ArrayList;
import java.util.List;

public class TripOrderListAdapter extends ArrayAdapter<TripOrder> {

    private Filter filter;
    private List<TripOrder> items;

    public TripOrderListAdapter(Context context, int resource, List<TripOrder> items) {
        super(context, resource, items);

        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;



        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.listview_orderlist, null);
        }

        TripOrder p = getItem(position);

        if(position %2 == 1)
        {
            // Set a background color for ListView regular row/item
            v.setBackgroundColor(Color.parseColor("#effaff"));
        }
        else
        {
            // Set the background color for alternate row/item
            v.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
        }

        if (p != null) {

            /*
            int payeeType = p.getMoneyTransferType();
            String account ="";
            String accountTitle = "Hesap No";
            if(payeeType== MoneyTransferType.TO_CREDITCARD){
                account = p.getPayeeCardNumber();
                accountTitle = "Kredit Kart No";
            }
            else{
                account = p.getPayeeAccountNumber();
                accountTitle = "Hesap No";
            }
            */


            TextView listview_orderlist_company = (TextView)v.findViewById(R.id.listview_orderlist_company);
            listview_orderlist_company.setText(p.getCompanyName());

            TextView listview_orderlist_branch = (TextView)v.findViewById(R.id.listview_orderlist_branch);
            listview_orderlist_branch.setText(p.getBranchName());

            ImageView imageView = (ImageView)v.findViewById(R.id.listview_orderlist_img_ok);

            if(p.getDeliveryStatusId()==4) {
                imageView.setImageResource(R.drawable.problem);
            }
            else if (p.getDeliveryStatusId()==3){
                imageView.setImageResource(R.drawable.ok);
            }
            else{
                imageView.setImageResource(R.drawable.notok);
            }



        }
        return v;

    }


}

