package com.xinerji.xinerjidc.model;

import java.io.Serializable;

/**
 * Created by msuras on 2/11/18.
 */

public class CustomerSharedData implements Serializable {

    private String Plaque;

    private String SessionToken;

    private long Id;

    private String CellPhoneNumber;

    private String CustomerName;

    private String TcIdentifier;

    public String getPlaque() {
        return Plaque;
    }


    public void setPlaque(String plaque) {
        Plaque = plaque;
    }

    public String getSessionToken() {
        return SessionToken;
    }

    public void setSessionToken(String sessionToken) {
        SessionToken = sessionToken;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getCellPhoneNumber() {
        return CellPhoneNumber;
    }

    public void setCellPhoneNumber(String cellPhoneNumber) {
        CellPhoneNumber = cellPhoneNumber;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getTcIdentifier() {
        return TcIdentifier;
    }

    public void setTcIdentifier(String tcIdentifier) {
        TcIdentifier = tcIdentifier;
    }
}
