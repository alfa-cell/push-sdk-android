package com.alfa.cell.push.sdk.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Register {

    @Expose
    private String deviceType = "ANDROID";
    @Expose
    private String deviceToken;
    @Expose
    private String deviceID;
    @Expose
    private String appCode;
    @Expose
    @SerializedName("appID")
    private String packageName;

    private String applicationID;

    public Register(String deviceToken, String deviceID, String packageName, String appCode) {
        this.deviceToken = deviceToken;
        this.deviceID = deviceID;
        this.packageName = packageName;
        this.appCode = appCode;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getApplicationID() {
        return applicationID;
    }

    public void setApplicationID(String applicationID) {
        this.applicationID = applicationID;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
}