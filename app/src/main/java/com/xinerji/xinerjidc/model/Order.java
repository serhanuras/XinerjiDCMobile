package com.xinerji.xinerjidc.model;

import java.io.Serializable;

public class Order implements Serializable {

    private long Id ;

    private long FirmId ;

    private long TripId ;

    private String Title ;

    private String Description ;

    private long CityId ;

    private long BranchId ;

    private String CompanyName ;

    private String BranchName ;

    private long DeliveryStatusId ;

    private String DeliveryStatus ;

    private long OrderTypeId ;

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

    public long getTripId() {
        return TripId;
    }

    public void setTripId(long tripId) {
        TripId = tripId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public long getCityId() {
        return CityId;
    }

    public void setCityId(long cityId) {
        CityId = cityId;
    }

    public long getBranchId() {
        return BranchId;
    }

    public void setBranchId(long branchId) {
        BranchId = branchId;
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

    public long getOrderTypeId() {
        return OrderTypeId;
    }

    public void setOrderTypeId(long orderTypeId) {
        OrderTypeId = orderTypeId;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }
}
