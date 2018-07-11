package com.xinerji.xinerjidc.model;

public class OrderDetailRow {

    private String title, value, phone;

    public OrderDetailRow(String title, String value, String phone){

        this.title = title;
        this.value = value;
        this.phone = phone;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
