package com.xinerji.xinerjidc.fragment;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xinerji.xinerjidc.R;
import com.xinerji.xinerjidc.activity.FragmentActivity;
import com.xinerji.xinerjidc.adapters.ImageListAdapter;
import com.xinerji.xinerjidc.adapters.NetDateTimeJsonAdapter;
import com.xinerji.xinerjidc.model.GetOrderDocumentListRequest;
import com.xinerji.xinerjidc.model.GetOrderDocumentListResponse;
import com.xinerji.xinerjidc.model.InsertOrderDocumentRequest;
import com.xinerji.xinerjidc.model.InsertOrderDocumentResponse;
import com.xinerji.xinerjidc.utilities.JsonServiceCallingUtil;
import com.xinerji.xinerjidc.utilities.PopupUtil;
import com.xinerji.xinerjidc.utilities.SessionUtil;
import com.xinerji.xinerjidc.utilities.XinerjiServiceUtil;

import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import okhttp3.Request;
import okhttp3.Response;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Belal on 1/23/2018.
 */

public class UploadImageFragment extends Fragment implements View.OnClickListener {

    JsonServiceCallingUtil jsonServiceCallingUtil;
    final PopupUtil popupUtil = new PopupUtil();
    Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new NetDateTimeJsonAdapter()).create();

    private Uri picUri;
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private GridView grid;

    private List<Bitmap> listOfBitmapImage;

    GetOrderDocumentListResponse getOrderDocumentListResponse;

    //public static final String GridViewDemo_ImagePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/GridViewDemo/";


    Button captureBtn = null;
    final int CAMERA_CAPTURE = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //just change the fragment_dashboard
        //with the fragment you want to inflate
        //like if the class is HomeFragment it should have R.layout.home_fragment
        //if it is DashboardFragment it should have R.layout.fragment_dashboard
        View view = inflater.inflate(R.layout.fragment_upload_image, null);

        captureBtn = (Button)view.findViewById(R.id.frame_upload_image_btn);
        captureBtn.setOnClickListener(this);
        grid = ( GridView) view.findViewById(R.id.frame_upload_image_gridviewimg);

        listOfBitmapImage = new ArrayList<Bitmap>();


        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                SessionUtil.setSelectedImageId(getOrderDocumentListResponse.getOrderDocumentList().get(i).getId());
                SessionUtil.setSelectedImageIndex(i);

                ImageDetailFragment imageDetailFragment = new ImageDetailFragment();
                android.support.v4.app.FragmentTransaction fragmentTransaction =
                        getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, imageDetailFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });



        return view;
    }

    @Override
    public void onClick(View arg0) {
        if (arg0.getId() == R.id.frame_upload_image_btn) {

            try {
                //use standard intent to capture an image
                Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //we will handle the returned data in onActivityResult
                startActivityForResult(captureIntent, CAMERA_CAPTURE);
            } catch(ActivityNotFoundException anfe){
                //display an error message
                String errorMessage = "Whoops - your device doesn't support capturing images!";
                Toast toast = Toast.makeText(this.getContext(), errorMessage, Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
        //user is returning from capturing an image using the camera
            if(requestCode == CAMERA_CAPTURE) {
                Bundle extras = data.getExtras();
                Bitmap thePic = extras.getParcelable("data");

                final ByteArrayOutputStream out = new ByteArrayOutputStream();
                thePic.compress(Bitmap.CompressFormat.JPEG, 80, out);

                final String encoded = Base64.encodeToString(out.toByteArray(), Base64.NO_WRAP);


                popupUtil.dismissShowLoadingPopUp();

                jsonServiceCallingUtil = new JsonServiceCallingUtil(getContext());

                jsonServiceCallingUtil.mDataLoadEvents = new JsonServiceCallingUtil.DataLoadEvents() {

                    @Override
                    public void onLoadingDataFinished() {

                        Response response = jsonServiceCallingUtil.getResponse();


                        if (response.code() == JsonServiceCallingUtil.RESPONSE_CODE_SUCCESS) {


                            JSONObject json = jsonServiceCallingUtil.getResponseJsonObject();

                            InsertOrderDocumentResponse insertOrderDocumentResponse =
                                    gson.fromJson(json.toString(), InsertOrderDocumentResponse.class);

                            if (insertOrderDocumentResponse.getHeader().getError().getErrorCode() ==
                                    com.xinerji.xinerjidc.model.Error.SUCCEED) {

                                popupUtil.showInformationPopUp(getActivity(), "Başarıyla kaydedilmiştir.");

                                byte[] decodeImageArray = Base64.decode(encoded, Base64.NO_WRAP);

                                Bitmap thePic = BitmapFactory.decodeStream(new ByteArrayInputStream(decodeImageArray));



                                try {
                                    out.close();
                                } catch (FileNotFoundException e) {
                                    e.getMessage();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                                //listOfBitmapImage.add(thePic);
                            } else {
                                popupUtil.showServiceErrorPopUp(getActivity(), insertOrderDocumentResponse.getHeader().getError());
                            }
                        } else {
                            popupUtil.showServiceCallException(getActivity());
                        }
                    }
                };

                InsertOrderDocumentRequest insertOrderDocumentRequest = new InsertOrderDocumentRequest();
                insertOrderDocumentRequest.setOrderId(SessionUtil.getSelectedTripOrder().getOrderId());
                insertOrderDocumentRequest.setFileBase64(encoded);


                Request request = XinerjiServiceUtil.getPostRequest(
                        XinerjiServiceUtil.ORDER_INSERTORDERDOCUMENT,
                        insertOrderDocumentRequest,
                        getActivity());

                popupUtil.showLoadingPopUp(getActivity());

                jsonServiceCallingUtil.LoadData(request);


                grid.setAdapter(new ImageListAdapter(getView().getContext(), listOfBitmapImage));

            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        jsonServiceCallingUtil = new JsonServiceCallingUtil(getContext());

        jsonServiceCallingUtil.mDataLoadEvents = new JsonServiceCallingUtil.DataLoadEvents() {

            @Override
            public void onLoadingDataFinished() {

                Response response = jsonServiceCallingUtil.getResponse();



                if (response.code() == JsonServiceCallingUtil.RESPONSE_CODE_SUCCESS) {


                    JSONObject json = jsonServiceCallingUtil.getResponseJsonObject();

                    getOrderDocumentListResponse =
                            gson.fromJson(json.toString(), GetOrderDocumentListResponse.class);

                    if (getOrderDocumentListResponse.getHeader().getError().getErrorCode() ==
                            com.xinerji.xinerjidc.model.Error.SUCCEED) {

                        //popupUtil.showInformationPopUp(FragmentActivity.this,"Başarıyla kaydedilmiştir.");

                        popupUtil.dismissShowLoadingPopUp();

                        listOfBitmapImage.clear();
                        byte[] decodeImageArray;
                        Bitmap thePic;
                        for(int i=0;i<getOrderDocumentListResponse.getOrderDocumentList().size();i++){

                           decodeImageArray = Base64.decode(getOrderDocumentListResponse.getOrderDocumentList().get(i).getFileBinary(), Base64.NO_WRAP);

                            thePic = BitmapFactory.decodeStream(new ByteArrayInputStream(decodeImageArray));

                            listOfBitmapImage.add(thePic);
                        }



                        SessionUtil.setSelectedImageList(listOfBitmapImage);

                        grid.setAdapter(new ImageListAdapter(getContext(), listOfBitmapImage));

                    }
                    else {

                        popupUtil.dismissShowLoadingPopUp();
                        popupUtil.showServiceErrorPopUp(getActivity(), getOrderDocumentListResponse.getHeader().getError());
                    }
                }
                else {

                    popupUtil.dismissShowLoadingPopUp();
                    popupUtil.showServiceCallException(getActivity());
                }
            }
        };

        GetOrderDocumentListRequest getOrderDocumentListRequest = new GetOrderDocumentListRequest();
        getOrderDocumentListRequest.setOrderId(SessionUtil.getSelectedTripOrder().getOrderId());


        Request request = XinerjiServiceUtil.getPostRequest(
                XinerjiServiceUtil.ORDER_GETORDERDOCUMENTLIST,
                getOrderDocumentListRequest,
                getActivity());

        popupUtil.showLoadingPopUp(getActivity());

        jsonServiceCallingUtil.LoadData(request);
    }


}