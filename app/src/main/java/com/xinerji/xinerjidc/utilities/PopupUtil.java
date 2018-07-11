package com.xinerji.xinerjidc.utilities;

import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.xinerji.xinerjidc.R;

public class PopupUtil {

    public  void  showServiceErrorPopUp(Activity activity, com.xinerji.xinerjidc.model.Error error){
        dismissShowLoadingPopUp();

        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(activity.LAYOUT_INFLATER_SERVICE);

        View v = inflater.inflate(R.layout.activity_popup, (ViewGroup)activity.findViewById(R.id.popup_rl_main));

        final PopupWindow popUyari = new PopupWindow(v,
                activity.getWindowManager().getDefaultDisplay().getWidth(),
                activity.getWindowManager().getDefaultDisplay().getHeight(), true);


        popUyari.showAtLocation(v, Gravity.CENTER, 0, 0);

        TextView txtPostBaslik = (TextView)v.findViewById(R.id.popup_tv_title);
        TextView txtPopIcerik = (TextView)v.findViewById(R.id.popup_tv_content);

        Button btnButton = (Button)v.findViewById(R.id.popup_bt_ok);

        txtPostBaslik.setText("Uyarı");
        txtPopIcerik.setText(error.getErrorDescriptionTR());

        btnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popUyari.dismiss();
            }
        });

    }

    public  void  showErrorPopUp(Activity activity, String message){
        dismissShowLoadingPopUp();

        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(activity.LAYOUT_INFLATER_SERVICE);

        View v = inflater.inflate(R.layout.activity_popup, (ViewGroup)activity.findViewById(R.id.popup_rl_main));

        final PopupWindow popUyari = new PopupWindow(v,
                activity.getWindowManager().getDefaultDisplay().getWidth(),
                activity.getWindowManager().getDefaultDisplay().getHeight(), true);


        popUyari.showAtLocation(v, Gravity.CENTER, 0, 0);

        TextView txtPostBaslik = (TextView)v.findViewById(R.id.popup_tv_title);
        TextView txtPopIcerik = (TextView)v.findViewById(R.id.popup_tv_content);

        Button btnButton = (Button)v.findViewById(R.id.popup_bt_ok);

        txtPostBaslik.setText("Uyarı");
        txtPopIcerik.setText(message);

        btnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popUyari.dismiss();
            }
        });

    }

    public  void  showErrorPopUp(Activity activity, String message, final Runnable func){
        dismissShowLoadingPopUp();

        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(activity.LAYOUT_INFLATER_SERVICE);

        View v = inflater.inflate(R.layout.activity_popup, (ViewGroup)activity.findViewById(R.id.popup_rl_main));

        final PopupWindow popUyari = new PopupWindow(v,
                activity.getWindowManager().getDefaultDisplay().getWidth(),
                activity.getWindowManager().getDefaultDisplay().getHeight(), true);


        popUyari.showAtLocation(v, Gravity.CENTER, 0, 0);

        TextView txtPostBaslik = (TextView)v.findViewById(R.id.popup_tv_title);
        TextView txtPopIcerik = (TextView)v.findViewById(R.id.popup_tv_content);

        Button btnButton = (Button)v.findViewById(R.id.popup_bt_ok);

        txtPostBaslik.setText("Uyarı");
        txtPopIcerik.setText(message);

        btnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popUyari.dismiss();

                func.run();

            }
        });

    }



    public  void  showInformationPopUp(Activity activity, String message){
        dismissShowLoadingPopUp();

        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(activity.LAYOUT_INFLATER_SERVICE);

        View v = inflater.inflate(R.layout.activity_popup, (ViewGroup)activity.findViewById(R.id.popup_rl_main));

        final PopupWindow popUyari = new PopupWindow(v,
                activity.getWindowManager().getDefaultDisplay().getWidth(),
                activity.getWindowManager().getDefaultDisplay().getHeight(), true);


        popUyari.showAtLocation(v, Gravity.CENTER, 0, 0);

        TextView txtPostBaslik = (TextView)v.findViewById(R.id.popup_tv_title);
        TextView txtPopIcerik = (TextView)v.findViewById(R.id.popup_tv_content);

        Button btnButton = (Button)v.findViewById(R.id.popup_bt_ok);

        txtPostBaslik.setText("Uyarı");
        txtPopIcerik.setText(message);

        btnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popUyari.dismiss();
            }
        });

    }

    public  void  showServiceCallException(Activity activity){
        dismissShowLoadingPopUp();
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(activity.LAYOUT_INFLATER_SERVICE);

        View v = inflater.inflate(R.layout.activity_popup, (ViewGroup)activity.findViewById(R.id.popup_rl_main));

        final PopupWindow popUyari = new PopupWindow(v,
                activity.getWindowManager().getDefaultDisplay().getWidth(),
                activity.getWindowManager().getDefaultDisplay().getHeight(), true);


        popUyari.showAtLocation(v, Gravity.CENTER, 0, 0);

        TextView txtPostBaslik = (TextView)v.findViewById(R.id.popup_tv_title);
        TextView txtPopIcerik = (TextView)v.findViewById(R.id.popup_tv_content);

        Button btnButton = (Button)v.findViewById(R.id.popup_bt_ok);

        txtPostBaslik.setText("Uyarı");
        txtPopIcerik.setText("Uygulama geçici olarak servis dışıdır, lütfen sonra tekrar deneyiniz. HATA:PU01");

        btnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popUyari.dismiss();
            }
        });

    }

    public  void  showServiceDownException(Activity activity){
        dismissShowLoadingPopUp();
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(activity.LAYOUT_INFLATER_SERVICE);

        View v = inflater.inflate(R.layout.activity_popup, (ViewGroup)activity.findViewById(R.id.popup_rl_main));

        final PopupWindow popUyari = new PopupWindow(v,
                activity.getWindowManager().getDefaultDisplay().getWidth(),
                activity.getWindowManager().getDefaultDisplay().getHeight(), true);


        popUyari.showAtLocation(v, Gravity.CENTER, 0, 0);

        TextView txtPostBaslik = (TextView)v.findViewById(R.id.popup_tv_title);
        TextView txtPopIcerik = (TextView)v.findViewById(R.id.popup_tv_content);

        Button btnButton = (Button)v.findViewById(R.id.popup_bt_ok);

        txtPostBaslik.setText("Uyarı");
        txtPopIcerik.setText("Uygulama geçici olarka servis dışıdır, lütfen sonra tekrar deneyiniz. HATA:PU02");

        btnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popUyari.dismiss();
            }
        });

    }

    private PopupWindow popLoading;
    public  void  showLoadingPopUp(Activity activity) {
        if (popLoading == null) {

            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(activity.LAYOUT_INFLATER_SERVICE);

            View v = inflater.inflate(R.layout.activity_loading_popup, (ViewGroup) activity.findViewById(R.id.loadingPopup_rl_main));

            popLoading = new PopupWindow(v,
                    activity.getWindowManager().getDefaultDisplay().getWidth(),
                    activity.getWindowManager().getDefaultDisplay().getHeight(), true);


            popLoading.showAtLocation(v, Gravity.CENTER, 0, 0);
        }
    }

    public void dismissShowLoadingPopUp(){

        if(popLoading!=null) {
            popLoading.dismiss();
            popLoading=null;
        }
    }

    public  void  showErrorMessageAndDirectToLogon(final Activity activity, String message){
        dismissShowLoadingPopUp();

        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(activity.LAYOUT_INFLATER_SERVICE);

        View v = inflater.inflate(R.layout.activity_popup, (ViewGroup)activity.findViewById(R.id.popup_rl_main));

        final PopupWindow popUyari = new PopupWindow(v,
                activity.getWindowManager().getDefaultDisplay().getWidth(),
                activity.getWindowManager().getDefaultDisplay().getHeight(), true);


        popUyari.showAtLocation(v, Gravity.CENTER, 0, 0);

        TextView txtPostBaslik = (TextView)v.findViewById(R.id.popup_tv_title);
        TextView txtPopIcerik = (TextView)v.findViewById(R.id.popup_tv_title);

        Button btnButton = (Button)v.findViewById(R.id.popup_bt_ok);

        txtPostBaslik.setText("Uyarı");
        txtPopIcerik.setText(message);

        btnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Intent myIntent = new Intent(activity, LogonActivity.class);
                //activity.startActivity(myIntent);


            }
        });

    }
}
