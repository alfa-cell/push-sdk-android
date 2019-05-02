package com.alfa.cell.push.sdk;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.alfa.cell.push.sdk.models.User;
import com.alfa.cell.push.sdk.models.delivery.DeliveryResult;
import com.alfa.cell.push.sdk.models.register.RegisterResult;
import com.alfa.cell.push.sdk.models.user.UserResult;

public class MobilyPushService {
    private static NetworkClient mNetworkClient;
    private static String mPackageName = "";

    public static void init(@NonNull String appCode, @NonNull String uniqueID, @Nullable String packageName) {
        mNetworkClient = new NetworkClient();
        mNetworkClient.init(appCode, uniqueID);
        mPackageName = packageName;
    }

    public static void init(@NonNull String appCode, @NonNull String uniqueID) {
        init(appCode, uniqueID, "");
    }

    public static void registerToken(String token, @Nullable RegisterListener registerListener) {
        mNetworkClient.registerToken(token, mPackageName, registerListener);
    }

    public static void pushDelivered(String pushId, @Nullable PushDataListener deliveryResult) {
        mNetworkClient.pushDelivered(pushId, deliveryResult);
    }

    public static void registerUser(User user, @Nullable UserRegisterListener registerListener) {
        mNetworkClient.registerUser(user, registerListener);
    }

    public interface RegisterListener {
        void onRegistered(RegisterResult result);

        void onError(Throwable throwable);
    }

    public interface UserRegisterListener {
        void onRegistered(UserResult result);

        void onError(Throwable throwable);
    }

    public interface PushDataListener {
        void onDelivered(DeliveryResult result);

        void onError(Throwable throwable);
    }
}