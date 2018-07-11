package com.xinerji.xinerjidc.model;

import java.io.Serializable;

public class SetTruckLocationRequest extends AbstractRequest implements Serializable {

    private long Id ;

    private String Latitude ;

    private String Longtitude ;


    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getLongtitude() {
        return Longtitude;
    }

    public void setLongtitude(String longtitude) {
        Longtitude = longtitude;
    }
}
