package com.xinerji.xinerjidc.model;

import java.io.Serializable;

public class Trip implements Serializable {

    private long Id;

    private long FirmId ;

    private String Name ;

    private String Truck ;

    private long TruckId ;

    private String Company ;

    private long ConsigneeId ;

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

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getTruck() {
        return Truck;
    }

    public void setTruck(String truck) {
        Truck = truck;
    }

    public long getTruckId() {
        return TruckId;
    }

    public void setTruckId(long truckId) {
        TruckId = truckId;
    }

    public String getCompany() {
        return Company;
    }

    public void setCompany(String company) {
        Company = company;
    }

    public long getConsigneeId() {
        return ConsigneeId;
    }

    public void setConsigneeId(long consigneeId) {
        ConsigneeId = consigneeId;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }
}
