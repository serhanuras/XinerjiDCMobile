package com.xinerji.xinerjidc.utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static Date addDays(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }


    public static String toyyyyMMdd(Date date){
        DateFormat df = new SimpleDateFormat("yyyyMMdd");

        return df.format(date);
    }

    public static String toShortDate(Date date){
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        return df.format(date);
    }

    public static String toTurkishCultureDateFormat(Date date){

        String dateString = "";

        dateString = date.getDate() + "/" +
                DateUtil.getMonthName(date.getMonth()).substring(0,3) + "/" +
                String.valueOf(1900 + date.getYear());

        return dateString;
    }

    public static String toTurkishCultureFormat(Date date){

        String dateString = "";

        dateString = date.getDate() + " / " +
                DateUtil.getMonthName(date.getMonth()) + " / " +
                String.valueOf(1900 + date.getYear());

        return dateString;
    }


    public static String getMonthName(int month){

        if(month==0)
            return "Ocak";
        else if(month==1)
            return  "Şubat";
        else if(month==2)
            return  "Mart";
        else if(month==3)
            return  "Nisan";
        else if(month==4)
            return  "Mayıs";
        else if(month==5)
            return  "Haziran";
        else if(month==6)
            return  "Temmuz";
        else if(month==7)
            return  "Ağustos";
        else if(month==8)
            return  "Eylül";
        else if(month==9)
            return  "Ekim";
        else if(month==10)
            return  "Kasım";
        else if(month==11)
            return  "Aralık";
        else
            return  "";
    }
}
