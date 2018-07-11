package com.xinerji.xinerjidc.model;

import java.io.Serializable;

public class Header implements Serializable {


    private Error Error;

    public Error getError() {
        return Error;
    }

    public void setError(Error error) {
        Error = error;
    }
}
