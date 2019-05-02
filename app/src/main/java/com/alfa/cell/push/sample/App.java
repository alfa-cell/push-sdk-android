package com.alfa.cell.push.sample;

import android.app.Application;

import com.alfa.cell.push.sdk.MobilyPushService;

public class App extends Application {
    private final String API_KEY = "5ccaac943b8f9c0012010398";

    @Override
    public void onCreate() {
        super.onCreate();

        MobilyPushService.init(API_KEY, Utils.getDeviceID(this));
    }
}
