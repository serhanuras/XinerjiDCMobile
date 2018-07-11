package com.xinerji.xinerjidc.model;

import java.io.Serializable;
import java.util.List;

public class GetOrderDocumentListResponse extends AbstractResponse implements Serializable {

    private List<OrderDocument> OrderDocumentList;

    public List<OrderDocument> getOrderDocumentList() {
        return OrderDocumentList;
    }

    public void setOrderDocumentList(List<OrderDocument> orderDocumentList) {
        OrderDocumentList = orderDocumentList;
    }
}
