package com.alfa.cell.push.sdk.models.delivery;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("messageId")
    @Expose
    private String messageId;
    @SerializedName("deviceUid")
    @Expose
    private String deviceUid;
    @SerializedName("action")
    @Expose
    private String action;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getDeviceUid() {
        return deviceUid;
    }

    public void setDeviceUid(String deviceUid) {
        this.deviceUid = deviceUid;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Data{" +
                "id='" + id + '\'' +
                ", messageId='" + messageId + '\'' +
                ", deviceUid='" + deviceUid + '\'' +
                ", action='" + action + '\'' +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }
}
