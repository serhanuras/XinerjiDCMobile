package com.xinerji.xinerjidc.utilities;

import android.graphics.Bitmap;

import com.xinerji.xinerjidc.model.GetOrderDocumentListResponse;
import com.xinerji.xinerjidc.model.GetTripByTruckIdResponse;
import com.xinerji.xinerjidc.model.Order;
import com.xinerji.xinerjidc.model.TripOrder;

import java.util.List;

public class SessionUtil {

    public static GetTripByTruckIdResponse getTripByTruckIdResponse;

    public static TripOrder selectedTripOrder;

    public static int selectedImageIndex;

    public static long selectedImageId;

    public static List<Bitmap> selectedImageList;


    public static GetTripByTruckIdResponse getGetTripByTruckIdResponse() {
        return SessionUtil.getTripByTruckIdResponse;
    }

    public static void setGetTripByTruckIdResponse(GetTripByTruckIdResponse getTripByTruckIdResponse) {
        SessionUtil.getTripByTruckIdResponse = getTripByTruckIdResponse;
    }

    public static TripOrder getSelectedTripOrder() {
        return selectedTripOrder;
    }

    public static void setSelectedTripOrder(TripOrder selectedTripOrder) {
        SessionUtil.selectedTripOrder = selectedTripOrder;
    }

    public static int getSelectedImageIndex() {
        return selectedImageIndex;
    }

    public static void setSelectedImageIndex(int selectedImageIndex) {
        SessionUtil.selectedImageIndex = selectedImageIndex;
    }

    public static List<Bitmap> getSelectedImageList() {
        return selectedImageList;
    }

    public static void setSelectedImageList(List<Bitmap> selectedImageList) {
        SessionUtil.selectedImageList = selectedImageList;
    }

    public static long getSelectedImageId() {
        return selectedImageId;
    }

    public static void setSelectedImageId(long selectedImageId) {
        SessionUtil.selectedImageId = selectedImageId;
    }
}
