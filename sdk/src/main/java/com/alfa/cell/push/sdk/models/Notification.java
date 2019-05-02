package com.alfa.cell.push.sdk.models;

public class Notification {

    private String pushId;
    private String deviceID;

    public Notification(String pushId, String deviceID) {
        this.pushId = pushId;
        this.deviceID = deviceID;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

}