package com.xinerji.xinerjidc.model;

import java.io.Serializable;

public class GetTripByTruckIdRequest extends AbstractRequest implements Serializable {

    private long TruckId;

    public long getTruckId() {
        return TruckId;
    }

    public void setTruckId(long truckId) {
        TruckId = truckId;
    }
}
