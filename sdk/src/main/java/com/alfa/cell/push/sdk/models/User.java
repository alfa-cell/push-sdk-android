package com.alfa.cell.push.sdk.models;

import androidx.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @Expose
    private String id;
    @Expose
    @SerializedName("deviceID")
    private String deviceId;
    @Expose
    private String appCode;
    @Expose
    @SerializedName("userID")
    private String userId;
    @Expose
    private String phone;
    @Expose
    private String username;
    @Expose
    private String email;

    public User(@Nullable String userId, @Nullable String phone,
                @Nullable String username, @Nullable String email) {
        this.userId = userId;
        this.phone = phone;
        this.username = username;
        this.email = email;
    }

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}