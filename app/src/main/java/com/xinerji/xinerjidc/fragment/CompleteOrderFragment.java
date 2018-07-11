package com.xinerji.xinerjidc.fragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xinerji.xinerjidc.R;
import com.xinerji.xinerjidc.activity.DashboardActivity;
import com.xinerji.xinerjidc.activity.FragmentActivity;
import com.xinerji.xinerjidc.activity.LogonActivity;
import com.xinerji.xinerjidc.activity.TripActivity;
import com.xinerji.xinerjidc.adapters.NetDateTimeJsonAdapter;
import com.xinerji.xinerjidc.model.ChangeDeliverStatusRequest;
import com.xinerji.xinerjidc.model.ChangeDeliverStatusResponse;
import com.xinerji.xinerjidc.model.GetTripByTruckIdRequest;
import com.xinerji.xinerjidc.model.GetTripByTruckIdResponse;
import com.xinerji.xinerjidc.model.Trip;
import com.xinerji.xinerjidc.model.TripOrder;
import com.xinerji.xinerjidc.model.TripResult;
import com.xinerji.xinerjidc.utilities.JsonServiceCallingUtil;
import com.xinerji.xinerjidc.utilities.PopupUtil;
import com.xinerji.xinerjidc.utilities.SessionUtil;
import com.xinerji.xinerjidc.utilities.XinerjiServiceUtil;

import org.json.JSONObject;

import java.util.Date;
import java.util.List;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Belal on 1/23/2018.
 */

public class CompleteOrderFragment extends Fragment {

    JsonServiceCallingUtil jsonServiceCallingUtil;
    final PopupUtil popupUtil = new PopupUtil();
    Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new NetDateTimeJsonAdapter()).create();

    Spinner spResult;
    RadioGroup rgResult;
    Button btn_next;

    private String[] problems={"Problem Nedeni","Adres Hatası", "Sipariş & Preseller Hatası", "Diğer"};
    private ArrayAdapter<String> dataAdapterForProblem;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //just change the fragment_dashboard
        //with the fragment you want to inflate
        //like if the class is HomeFragment it should have R.layout.home_fragment
        //if it is DashboardFragment it should have R.layout.fragment_dashboard
        final View view = inflater.inflate(R.layout.fragment_complete_order, null);

        spResult = view.findViewById(R.id.fragment_complete_order_sp_result);
        rgResult = view.findViewById(R.id.fragment_complete_order_rg_result);
        btn_next = view.findViewById(R.id.fragment_complete_order_btn_next);

        dataAdapterForProblem = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_item, problems);
        dataAdapterForProblem.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spResult.setAdapter(dataAdapterForProblem);
        spResult.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(view.getContext(), spResult.getSelectedItem().toString(),
                        Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!(spResult.getSelectedItemPosition()==0 &&
                        rgResult.getCheckedRadioButtonId()==R.id.fragment_complete_rb_has_problem)) {
                    GetTripByTruckIdResponse getTripByTruckIdResponse = SessionUtil.getGetTripByTruckIdResponse();

                    List<TripOrder> tripOrderList = getTripByTruckIdResponse.getTripOrderList();


                    for (int i = 0; i < tripOrderList.size(); i++) {
                        TripOrder tripOrder = tripOrderList.get(i);

                        if (tripOrder.getOrderId() == SessionUtil.getSelectedTripOrder().getOrderId()) {

                            TripResult tripResult = new TripResult();

                            switch (rgResult.getCheckedRadioButtonId()) {
                                case R.id.fragment_complete_rb_not_problem:
                                    tripResult.setDeliveryStatusId(3);
                                    break;
                                case R.id.fragment_complete_rb_has_problem:
                                    tripResult.setDeliveryStatusId(4);

                                    tripResult.setProblemId(spResult.getSelectedItemPosition());

                                    break;

                            }

                            tripOrder.setTripResult(tripResult);
                            tripOrder.setDeliveryStatusId(tripResult.getDeliveryStatusId());

                            SessionUtil.getSelectedTripOrder().setTripResult(tripResult);

                            jsonServiceCallingUtil = new JsonServiceCallingUtil(view.getContext());

                            jsonServiceCallingUtil.mDataLoadEvents = new JsonServiceCallingUtil.DataLoadEvents() {

                                @Override
                                public void onLoadingDataFinished() {

                                    Response response = jsonServiceCallingUtil.getResponse();

                                    popupUtil.dismissShowLoadingPopUp();

                                    if (response.code() == JsonServiceCallingUtil.RESPONSE_CODE_SUCCESS) {


                                        JSONObject json = jsonServiceCallingUtil.getResponseJsonObject();

                                        ChangeDeliverStatusResponse changeDeliverStatusResponse =
                                                gson.fromJson(json.toString(), ChangeDeliverStatusResponse.class);

                                        if (changeDeliverStatusResponse.getHeader().getError().getErrorCode() ==
                                                com.xinerji.xinerjidc.model.Error.SUCCEED) {

                                            popupUtil.showInformationPopUp(getActivity(),"Başarıyla kaydedilmiştir.");

                                        }
                                        else {
                                            popupUtil.showServiceErrorPopUp(getActivity(), changeDeliverStatusResponse.getHeader().getError());
                                        }
                                    }
                                    else {
                                        popupUtil.showServiceCallException(getActivity());
                                    }
                                }
                            };

                            ChangeDeliverStatusRequest changeDeliverStatusRequest = new ChangeDeliverStatusRequest();
                            changeDeliverStatusRequest.setOrderId(SessionUtil.getSelectedTripOrder().getOrderId());
                            changeDeliverStatusRequest.setDeliveryStatusId(tripResult.getDeliveryStatusId());
                            changeDeliverStatusRequest.setDeliverySubStatusId(tripResult.getProblemId());


                            Request request = XinerjiServiceUtil.getPostRequest(
                                    XinerjiServiceUtil.ORDER_CHANGEDELIVERSTATUS,
                                    changeDeliverStatusRequest,
                                    getActivity());

                            popupUtil.showLoadingPopUp(getActivity());

                            jsonServiceCallingUtil.LoadData(request);

                            /*
                            UploadImageFragment uploadImageFragment = new UploadImageFragment();
                            android.support.v4.app.FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                            fragmentTransaction.replace(R.id.fragment_container, uploadImageFragment);
                            fragmentTransaction.addToBackStack(null);
                            fragmentTransaction.commit();
                            */
                        }
                    }

                }
                else{
                    popupUtil.showInformationPopUp(getActivity(), "Problem Tipi Seçiniz.");
                }
            }
        });

        rgResult.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                switch(i)
                {
                    case R.id.fragment_complete_rb_not_problem:
                        spResult.setVisibility(View.GONE);
                        break;
                    case R.id.fragment_complete_rb_has_problem:
                        spResult.setVisibility(View.VISIBLE);
                        break;

                }
            }
        });


        TripOrder tripOrder = SessionUtil.getSelectedTripOrder();


            if(tripOrder.getDeliveryStatusId()==3){

                ((RadioButton)rgResult.getChildAt(0)).setChecked(true);
                spResult.setVisibility(View.GONE);
            }
            else{

                ((RadioButton)rgResult.getChildAt(1)).setChecked(true);

                spResult.setVisibility(View.VISIBLE);

                spResult.setSelection(Integer.parseInt(Long.toString(tripOrder.getDeliverySubStatusId())));
            }



        return view;
    }

    @Override
    public void onStart() {
        super.onStart();


    }
}