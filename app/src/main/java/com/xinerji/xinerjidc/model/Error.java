package com.xinerji.xinerjidc.model;

import java.util.Date;

public class Error {

    public static int SUCCEED=0;

    public static int SESSION_NOT_FOUND=998;

    private int Id;

    private int ChannelCode;

    private int ErrorCode;

    private String ErrorDescriptionTR;

    private String ErrorDescriptionENG;

    private Date DateLastModified;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getChannelCode() {
        return ChannelCode;
    }

    public void setChannelCode(int channelCode) {
        ChannelCode = channelCode;
    }

    public int getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(int errorCode) {
        ErrorCode = errorCode;
    }

    public String getErrorDescriptionTR() {
        return ErrorDescriptionTR;
    }

    public void setErrorDescriptionTR(String errorDescriptionTR) {
        ErrorDescriptionTR = errorDescriptionTR;
    }

    public String getErrorDescriptionENG() {
        return ErrorDescriptionENG;
    }

    public void setErrorDescriptionENG(String errorDescriptionENG) {
        ErrorDescriptionENG = errorDescriptionENG;
    }

    public Date getDateLastModified() {
        return DateLastModified;
    }

    public void setDateLastModified(Date dateLastModified) {
        DateLastModified = dateLastModified;
    }

}
