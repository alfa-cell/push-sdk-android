package com.alfa.cell.push.sdk.models.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserResult {

    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("error")
    @Expose
    private String error;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
