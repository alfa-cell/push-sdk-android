package com.alfa.cell.push.sample;

import android.content.Context;
import android.provider.Settings;

public class Utils {

    public static String getDeviceID(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

}
