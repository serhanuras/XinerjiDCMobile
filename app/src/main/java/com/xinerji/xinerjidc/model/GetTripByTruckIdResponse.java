package com.xinerji.xinerjidc.model;

import java.io.Serializable;
import java.util.List;

public class GetTripByTruckIdResponse extends AbstractResponse implements Serializable {

    private Trip Trip;

    private List<TripOrder> TripOrderList;

    public com.xinerji.xinerjidc.model.Trip getTrip() {
        return Trip;
    }

    public void setTrip(com.xinerji.xinerjidc.model.Trip trip) {
        Trip = trip;
    }

    public List<TripOrder> getTripOrderList() {
        return TripOrderList;
    }

    public void setTripOrderList(List<TripOrder> tripOrderList) {
        TripOrderList = tripOrderList;
    }
}
