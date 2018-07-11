package com.xinerji.xinerjidc.model;

import java.io.Serializable;

public class GetOrderDocumentListRequest extends AbstractRequest implements Serializable {

    private long OrderId ;

    public long getOrderId() {
        return OrderId;
    }

    public void setOrderId(long orderId) {
        OrderId = orderId;
    }
}
