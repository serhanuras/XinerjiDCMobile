package com.xinerji.xinerjidc.utilities;

import android.app.Activity;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xinerji.xinerjidc.adapters.NetDateTimeJsonAdapter;
import com.xinerji.xinerjidc.model.AbstractRequest;
import com.xinerji.xinerjidc.model.AbstractResponse;

import org.json.JSONObject;

import java.util.Date;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by msuras on 1/11/18.
 */

public class XinerjiServiceUtil {

    public static String AUTHENTICATION_VALIDATEMOBILELOGON = "/authentication/validatemobilelogon";
    public static String TRIP_GETTRIPBYTRUCKID = "/trip/gettripbytruckid";
    public static String TRIP_SETTRUCKLOCATION = "/trip/settrucklocation";
    public static String ORDER_CHANGEDELIVERSTATUS ="/order/changedeliverstatus";
    public static String ORDER_INSERTORDERDOCUMENT = "/order/insertorderdocument";
    public static String ORDER_GETORDERDOCUMENTLIST = "/order/getorderdocumentlist";
    public static String ORDER_DELETEDOCUMENT = "/order/deleteorderdocument";

    private String responseString;

    private static Gson gson =
            new GsonBuilder()
                    .registerTypeAdapter(Date.class, new NetDateTimeJsonAdapter())
                    .create();

    public static final MediaType MEDIA_TYPE =
            MediaType.parse("application/json");

    public static Request doGetRequest(String servicename, Activity activity) {
        String token ="";

        //if(SessionUtil.getValidateLogonResponse()!=null)
        token = SharedPreferancesUtil.GetCustomerSharedData(activity).getSessionToken();

        Request request = new Request.Builder()
                .url(GetUrl(servicename))
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", token)
                .addHeader("cache-control", "no-cache")
                .addHeader("Authentication", token)
                .build();

        return request;
    }


    public static Request getPostRequest(String servicename, AbstractRequest requestObj, Activity activity){

        String token ="";
        token = SharedPreferancesUtil.GetCustomerSharedData(activity).getSessionToken();

        requestObj.Token = token;

        RequestBody body = RequestBody.create(MEDIA_TYPE,
                gson.toJson(requestObj));


        return new Request.Builder()
                .url(GetUrl(servicename))
                .post(body)
                .addHeader("Content-Type", "application/json")
                //.addHeader("Authorization", token)
                .addHeader("cache-control", "no-cache")
                //.addHeader("Authentication", token)
                .build();

    }

    public static Request getPostRequest(Gson gson, String servicename, AbstractRequest requestObj, Activity activity){
        RequestBody body = RequestBody.create(MEDIA_TYPE,
                gson.toJson(requestObj));

        String token ="";

        token = SharedPreferancesUtil.GetCustomerSharedData(activity).getSessionToken();

        return new Request.Builder()
                .url(GetUrl(servicename))
                .post(body)
                .addHeader("Content-Type", "application/json")
                //.addHeader("Authorization", token)
                .addHeader("cache-control", "no-cache")
                //.addHeader("Authentication", token)
                .build();

    }


    public static String GetUrl(String servicename){

        return "http://185.198.198.75:456" + servicename;
    }


    public static <T extends AbstractResponse> T ParseResponse(final Class<T> targetClass, Activity activity, Response response) throws Exception{
        T target = targetClass.newInstance();

        String mMessage = response.body().string();
        if (response.isSuccessful()) {
            try {
                String token = response.header("Authentication");
                if (token != null)
                    Log.w("Token:", token);

                JSONObject json = new JSONObject(mMessage);
                String serverResponse = json.toString();

                Log.w("Server Response", serverResponse);

                target = gson.fromJson(serverResponse, targetClass);

                if (target.getHeader().getError().getId() ==
                        com.xinerji.xinerjidc.model.Error.SUCCEED) {

                } else {
                    Log.w("Error Description :", target.getHeader().getError().getErrorDescriptionTR());

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
        }




        return target;

    }








}
