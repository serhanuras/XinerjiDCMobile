package com.xinerji.xinerjidc.utilities;

import android.app.Activity;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xinerji.xinerjidc.adapters.NetDateTimeJsonAdapter;
import com.xinerji.xinerjidc.model.CustomerSharedData;

import java.util.Date;



/**
 * Created by msuras on 1/15/18.
 */

public class SharedPreferancesUtil {



    private static String CUSTOMER_SHARED_DATE = "customerSharedData";

    private static Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new NetDateTimeJsonAdapter()).create();



    public static CustomerSharedData GetCustomerSharedData(Activity activity){
        SharedPreferences pref = activity.getSharedPreferences(activity.getPackageName(), activity.MODE_PRIVATE);

        String sCustomerSharedData = pref.getString(CUSTOMER_SHARED_DATE,"");

        if(sCustomerSharedData.equals("")){
            CustomerSharedData customerSharedData =  new CustomerSharedData();
            customerSharedData.setCustomerName("");
            customerSharedData.setSessionToken("");
            customerSharedData.setCellPhoneNumber("");
            customerSharedData.setPlaque("");
            customerSharedData.setTcIdentifier("");

            return customerSharedData;
        }
        else{
            return gson.fromJson(sCustomerSharedData, CustomerSharedData.class);
        }
    }


    public static void SetCustomerSharedData(Activity activity, CustomerSharedData customerSharedData){

        String sCustomerSharedData = gson.toJson(customerSharedData);

        SharedPreferences pref = activity.getSharedPreferences(activity.getPackageName(), activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        editor.putString(CUSTOMER_SHARED_DATE, sCustomerSharedData);
        editor.commit();

    }




}
