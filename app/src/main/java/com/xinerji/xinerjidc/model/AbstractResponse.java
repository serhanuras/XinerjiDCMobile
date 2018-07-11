package com.xinerji.xinerjidc.model;

import java.io.Serializable;

public class AbstractResponse implements Serializable {
    private Header Header;


    public Header getHeader() {
        return Header;
    }

    public void setHeader(Header header) {
        Header = header;
    }

}
