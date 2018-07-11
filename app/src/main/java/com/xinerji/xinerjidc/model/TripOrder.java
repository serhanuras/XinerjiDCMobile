package com.xinerji.xinerjidc.model;

import java.io.Serializable;
import java.util.List;

public class TripOrder implements Serializable {

    private long OrderId ;

    private String Title ;

    private String ConsignmentNo ;

    private String ReceiptNo ;

    private String Description ;

    private String CompanyName ;

    private String BranchName ;

    private String Location ;

    private String Adress ;

    private long DeliveryStatusId ;

    private long DeliverySubStatusId;

    private String DeliveryStatus ;

    private TripResult TripResult;

    private List<OrderRepresenter> OrderRepresenterList ;

    private List<OrderDetail> OrderDetailList ;

    public long getOrderId() {
        return OrderId;
    }

    public void setOrderId(long orderId) {
        OrderId = orderId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getConsignmentNo() {
        return ConsignmentNo;
    }

    public void setConsignmentNo(String consignmentNo) {
        ConsignmentNo = consignmentNo;
    }

    public String getReceiptNo() {
        return ReceiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        ReceiptNo = receiptNo;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public String getBranchName() {
        return BranchName;
    }

    public void setBranchName(String branchName) {
        BranchName = branchName;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getAdress() {
        return Adress;
    }

    public void setAdress(String adress) {
        Adress = adress;
    }

    public long getDeliveryStatusId() {
        return DeliveryStatusId;
    }

    public void setDeliveryStatusId(long deliveryStatusId) {
        DeliveryStatusId = deliveryStatusId;
    }

    public String getDeliveryStatus() {
        return DeliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        DeliveryStatus = deliveryStatus;
    }

    public List<OrderRepresenter> getOrderRepresenterList() {
        return OrderRepresenterList;
    }

    public void setOrderRepresenterList(List<OrderRepresenter> orderRepresenterList) {
        OrderRepresenterList = orderRepresenterList;
    }

    public List<OrderDetail> getOrderDetailList() {
        return OrderDetailList;
    }

    public void setOrderDetailList(List<OrderDetail> orderDetailList) {
        OrderDetailList = orderDetailList;
    }

    public com.xinerji.xinerjidc.model.TripResult getTripResult() {
        return TripResult;
    }

    public void setTripResult(com.xinerji.xinerjidc.model.TripResult tripResult) {
        TripResult = tripResult;
    }

    public long getDeliverySubStatusId() {
        return DeliverySubStatusId;
    }

    public void setDeliverySubStatusId(long deliverySubStatusId) {
        DeliverySubStatusId = deliverySubStatusId;
    }
}
