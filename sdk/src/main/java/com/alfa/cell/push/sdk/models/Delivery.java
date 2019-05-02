package com.alfa.cell.push.sdk.models;

import com.google.gson.annotations.Expose;

public class Delivery {

    @Expose
    private String messageId;
    @Expose
    private String deviceID;

    @Expose
    private String appCode;

    public Delivery(String messageId, String deviceID, String appCode) {
        this.messageId = messageId;
        this.deviceID = deviceID;
        this.appCode = appCode;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }
}