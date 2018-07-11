package com.xinerji.xinerjidc.model;

import java.io.Serializable;

public class ValidateMobileLogonRequest extends AbstractRequest implements Serializable {

    private String Plaque;

    private String TCIdentifier;


    public String getPlaque() {
        return Plaque;
    }

    public void setPlaque(String plaque) {
        Plaque = plaque;
    }

    public String getTCIdentifier() {
        return TCIdentifier;
    }

    public void setTCIdentifier(String TCIdentifier) {
        this.TCIdentifier = TCIdentifier;
    }
}
