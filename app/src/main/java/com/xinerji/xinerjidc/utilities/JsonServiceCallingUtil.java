package com.xinerji.xinerjidc.utilities;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;

import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.Request;

public class JsonServiceCallingUtil {

    public static final int RESPONSE_CODE_SUCCESS = 200;

    OkHttpClient client = new OkHttpClient();

    /*OkHttpClient client = new OkHttpClient.Builder()
            .connectionSpecs(Arrays.asList(
                    ConnectionSpec.MODERN_TLS,
                    ConnectionSpec.COMPATIBLE_TLS))
            .build();*/

    Context mContext;

    private JSONObject responseObject;

    private Response response;


    final PopupUtil popupUtil = new PopupUtil();

    private boolean mIsLoading = false;
    private AsyncTask mCurrentAsyncTask;

    public interface DataLoadEvents {
        void onLoadingDataFinished();
    }

    public DataLoadEvents mDataLoadEvents;

    public JSONObject getResponseJsonObject() {
        return responseObject;
    }

    public Response getResponse() {
        return response;
    }

    public AsyncTask getCurrentAsyncTask() { return mCurrentAsyncTask; }

    public boolean getIsLoading() {return mIsLoading; }

    public JsonServiceCallingUtil(Context pContext) {
        mContext = pContext;
    }

    public void LoadData(Request request) {

        mCurrentAsyncTask = new HttpAsyncTaskLoadData().execute(request);
    }

    private class HttpAsyncTaskLoadData extends AsyncTask<Request, Void, JSONObject> {
        @Override
        protected JSONObject doInBackground(Request... requests) {

            try {
                Request request =  requests[0];

                response = client.newCall(request).execute();

                if(response.code() == RESPONSE_CODE_SUCCESS)
                {
                    String result =  response.body().string();
                    return new JSONObject(result);
                }

            } catch (IOException e) {
                e.printStackTrace();
                //popupUtil.showServiceCallException((Activity)mContext);
            } catch (JSONException e) {
                e.printStackTrace();
                //popupUtil.showServiceCallException((Activity)mContext);
            }

            return null;
        }

        @Override
        protected void onPostExecute(JSONObject pResponse) {
            try {

                if (pResponse != null) {
                    responseObject = pResponse;
                }

                mIsLoading = false;
                mDataLoadEvents.onLoadingDataFinished();

            } catch (Exception e) {
                e.printStackTrace();
                //popupUtil.showServiceCallException((Activity)mContext);
            }
        }

        @Override
        protected void onCancelled() {
            mIsLoading = false;

            mDataLoadEvents.onLoadingDataFinished();
        }
    }
}
