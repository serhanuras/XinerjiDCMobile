package com.xinerji.xinerjidc.model;

import java.io.Serializable;

public class InsertOrderDocumentRequest extends AbstractRequest implements Serializable {

    private long OrderId ;

    private String FileBase64 ;

    public long getOrderId() {
        return OrderId;
    }

    public void setOrderId(long orderId) {
        OrderId = orderId;
    }

    public String getFileBase64() {
        return FileBase64;
    }

    public void setFileBase64(String fileBase64) {
        FileBase64 = fileBase64;
    }
}
