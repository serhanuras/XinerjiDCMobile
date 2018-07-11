package com.xinerji.xinerjidc.utilities;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by msuras on 1/11/18.
 */

public class UtilMethods {

    public static void hideKeyboard(View view, Activity activity) {
        InputMethodManager inputMethodManager =(InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static String priceToString(double price, String currencyType){

        //NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.ENGLISH);

        String toShow = priceWithoutDecimal(price);


        if (toShow.indexOf(".") > 0) {
            toShow = priceWithDecimal(price) + " " + currencyType;
        } else {
            toShow = priceWithoutDecimal(price) + " " + currencyType;
        }


        toShow = toShow.replace('.','#');
        toShow = toShow.replace(',', '.');
        toShow = toShow.replace('#', ',');

        if(toShow.indexOf(",")==0)
            toShow = "0"+ toShow;


        return toShow;

    }

    public static String priceWithDecimal (Double price) {
        DecimalFormat formatter = new DecimalFormat("###,###,###.00");
        return formatter.format(price);
    }

    public static String priceWithoutDecimal (Double price) {
        DecimalFormat formatter = new DecimalFormat("###,###,###.##");
        return formatter.format(price);
    }

    public static String formatDate(Date date){
        SimpleDateFormat dt1 = new SimpleDateFormat("dd/MM/yyyy");
        return dt1.format(date);
    }



    public static String getMobileDeviceId(Activity activity){

        /*
        final TelephonyManager tm = (TelephonyManager) activity.getBaseContext().getSystemService(Context.TELEPHONY_SERVICE);

        final String tmDevice, tmSerial, androidId;
        tmDevice = "" + tm.getDeviceId();
        tmSerial = "" + tm.getSimSerialNumber();
        androidId = "" + android.provider.Settings.Secure.getString(activity.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);

        UUID deviceUuid = new UUID(androidId.hashCode(), ((long)tmDevice.hashCode() << 32) | tmSerial.hashCode());
        String deviceId = deviceUuid.toString();

*/
        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_PHONE_STATE);

        if(result == PackageManager.PERMISSION_GRANTED) {
            String deviceId;
            TelephonyManager mTelephony = (TelephonyManager) activity.getSystemService(Context.TELEPHONY_SERVICE);
            if (mTelephony.getDeviceId() != null) {
                deviceId = mTelephony.getDeviceId();
            } else {
                deviceId = Settings.Secure.getString(activity.getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
            }
            return deviceId;
        }
        else
        {
            return "";
        }

    }


    public static String getCellPhoneNumber(Activity activity){

        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_PHONE_STATE);

        if(result == PackageManager.PERMISSION_GRANTED) {
            String cellNumber;
            TelephonyManager mTelephony = (TelephonyManager) activity.getSystemService(Context.TELEPHONY_SERVICE);

            cellNumber = mTelephony.getLine1Number();

            return cellNumber;
        }
        else
        {
            return "";
        }

    }



}
