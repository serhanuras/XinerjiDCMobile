package com.xinerji.xinerjidc.model;

import java.io.Serializable;

public class OrderRepresenter implements Serializable {

    private long Id;

    private long OrderId;

    private long RepresenterId;

    private int Level;

    private String Name;

    private String Email;

    private String Phone;

    private String Type;

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public long getOrderId() {
        return OrderId;
    }

    public void setOrderId(long orderId) {
        OrderId = orderId;
    }

    public long getRepresenterId() {
        return RepresenterId;
    }

    public void setRepresenterId(long representerId) {
        RepresenterId = representerId;
    }

    public int getLevel() {
        return Level;
    }

    public void setLevel(int level) {
        Level = level;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }
}
