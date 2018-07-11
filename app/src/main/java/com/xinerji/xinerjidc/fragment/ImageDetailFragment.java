package com.xinerji.xinerjidc.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xinerji.xinerjidc.R;
import com.xinerji.xinerjidc.adapters.NetDateTimeJsonAdapter;
import com.xinerji.xinerjidc.model.DeleteOrderDocumentRequest;
import com.xinerji.xinerjidc.model.DeleteOrderDocumentResponse;
import com.xinerji.xinerjidc.model.InsertOrderDocumentRequest;
import com.xinerji.xinerjidc.model.InsertOrderDocumentResponse;
import com.xinerji.xinerjidc.utilities.JsonServiceCallingUtil;
import com.xinerji.xinerjidc.utilities.PopupUtil;
import com.xinerji.xinerjidc.utilities.SessionUtil;
import com.xinerji.xinerjidc.utilities.XinerjiServiceUtil;

import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Belal on 1/23/2018.
 */

public class ImageDetailFragment extends Fragment {

    JsonServiceCallingUtil jsonServiceCallingUtil;
    final PopupUtil popupUtil = new PopupUtil();
    Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new NetDateTimeJsonAdapter()).create();

    private ImageView img;
    private Button btn;

    List<Bitmap> bitmapList = SessionUtil.getSelectedImageList();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //just change the fragment_dashboard
        //with the fragment you want to inflate
        //like if the class is HomeFragment it should have R.layout.home_fragment
        //if it is DashboardFragment it should have R.layout.fragment_dashboard
        View view = inflater.inflate(R.layout.fragment_image_detail, null);

        btn = (Button) view.findViewById(R.id.fragment_image_detail_btn);
        img = (ImageView) view.findViewById(R.id.fragment_image_detail_img);

        Bitmap image= bitmapList.get(SessionUtil.getSelectedImageIndex());

        img.setImageBitmap(image);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                popupUtil.dismissShowLoadingPopUp();

                jsonServiceCallingUtil = new JsonServiceCallingUtil(getContext());

                jsonServiceCallingUtil.mDataLoadEvents = new JsonServiceCallingUtil.DataLoadEvents() {

                    @Override
                    public void onLoadingDataFinished() {

                        Response response = jsonServiceCallingUtil.getResponse();

                        popupUtil.dismissShowLoadingPopUp();

                        if (response.code() == JsonServiceCallingUtil.RESPONSE_CODE_SUCCESS) {


                            JSONObject json = jsonServiceCallingUtil.getResponseJsonObject();

                            DeleteOrderDocumentResponse deleteOrderDocumentResponse =
                                    gson.fromJson(json.toString(), DeleteOrderDocumentResponse.class);

                            if (deleteOrderDocumentResponse.getHeader().getError().getErrorCode() ==
                                    com.xinerji.xinerjidc.model.Error.SUCCEED) {

                                bitmapList.remove(SessionUtil.getSelectedImageIndex());

                                SessionUtil.setSelectedImageList(bitmapList);

                                UploadImageFragment uploadImageFragment = new UploadImageFragment();
                                android.support.v4.app.FragmentTransaction fragmentTransaction =
                                        getFragmentManager().beginTransaction();
                                fragmentTransaction.replace(R.id.fragment_container, uploadImageFragment);
                                fragmentTransaction.addToBackStack(null);
                                fragmentTransaction.commit();

                            } else {
                                popupUtil.showServiceErrorPopUp(getActivity(), deleteOrderDocumentResponse.getHeader().getError());
                            }
                        } else {
                            popupUtil.showServiceCallException(getActivity());
                        }
                    }
                };

                DeleteOrderDocumentRequest deleteOrderDocumentRequest = new DeleteOrderDocumentRequest();
                deleteOrderDocumentRequest.setId(SessionUtil.getSelectedImageId());


                Request request = XinerjiServiceUtil.getPostRequest(
                        XinerjiServiceUtil.ORDER_DELETEDOCUMENT,
                        deleteOrderDocumentRequest,
                        getActivity());

                popupUtil.showLoadingPopUp(getActivity());

                jsonServiceCallingUtil.LoadData(request);




            }
        });



        return view;
    }
}