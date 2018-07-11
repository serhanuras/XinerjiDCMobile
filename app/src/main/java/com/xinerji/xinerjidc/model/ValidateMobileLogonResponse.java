package com.xinerji.xinerjidc.model;

import java.io.Serializable;

public class ValidateMobileLogonResponse extends AbstractResponse implements Serializable {

    private String SessionNumber;

    private String Name;

    private long TruckId;

    public String getSessionNumber() {
        return SessionNumber;
    }

    public void setSessionNumber(String sessionNumber) {
        SessionNumber = sessionNumber;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }


    public long getTruckId() {
        return TruckId;
    }

    public void setTruckId(long truckId) {
        TruckId = truckId;
    }
}
