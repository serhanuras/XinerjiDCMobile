package com.xinerji.xinerjidc.model;

import java.io.Serializable;

public class OrderDetail implements Serializable {

    private long Id ;

    private long FirmId ;

    private long OrderId ;

    private String ProductDescription ;

    private long ProductId ;

    private int Quantity ;

    private int Status ;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public long getFirmId() {
        return FirmId;
    }

    public void setFirmId(long firmId) {
        FirmId = firmId;
    }

    public long getOrderId() {
        return OrderId;
    }

    public void setOrderId(long orderId) {
        OrderId = orderId;
    }

    public String getProductDescription() {
        return ProductDescription;
    }

    public void setProductDescription(String productDescription) {
        ProductDescription = productDescription;
    }

    public long getProductId() {
        return ProductId;
    }

    public void setProductId(long productId) {
        ProductId = productId;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }
}
