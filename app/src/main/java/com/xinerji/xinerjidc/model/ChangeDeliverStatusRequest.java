package com.xinerji.xinerjidc.model;

import java.io.Serializable;

public class ChangeDeliverStatusRequest extends AbstractRequest implements Serializable {

    private long OrderId ;

    private long DeliveryStatusId ;

    private long DeliverySubStatusId ;

    public long getOrderId() {
        return OrderId;
    }

    public void setOrderId(long orderId) {
        OrderId = orderId;
    }

    public long getDeliveryStatusId() {
        return DeliveryStatusId;
    }

    public void setDeliveryStatusId(long deliveryStatusId) {
        DeliveryStatusId = deliveryStatusId;
    }

    public long getDeliverySubStatusId() {
        return DeliverySubStatusId;
    }

    public void setDeliverySubStatusId(long deliverySubStatusId) {
        DeliverySubStatusId = deliverySubStatusId;
    }
}
