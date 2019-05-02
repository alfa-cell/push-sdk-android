package com.alfa.cell.push.sample;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.alfa.cell.push.sdk.MobilyPushService;
import com.alfa.cell.push.sdk.models.delivery.DeliveryResult;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;


public class FcmService extends FirebaseMessagingService {
    private static final String CHANNEL_ID = "com.alfa.cell.push.sample.channel.id";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        createNotificationChannel();
        if (remoteMessage.getData().size() > 0) {
            if (remoteMessage.getData().containsKey("messageId")) {
                String pushId = remoteMessage.getData().get("messageId");
                String title = "Sample Title";
                String body = "Sample body";
                if (remoteMessage.getData().containsKey("title")) {
                    title = remoteMessage.getData().get("title");
                }
                if (remoteMessage.getData().containsKey("body")) {
                    body = remoteMessage.getData().get("body");
                }
                createNotification(title, title + " " + body);
                MobilyPushService.pushDelivered(pushId, new MobilyPushService.PushDataListener() {
                    @Override
                    public void onDelivered(DeliveryResult result) {
                        Log.e("tag", result.getData().toString());
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        Log.e("tag", throwable.getMessage());
                    }
                });
            }
        }
    }

    private void createNotification(String title, String message) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.drawable.ic_notify_icon)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(message))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(1, builder.build());
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID,
                    "com.alfa.cell.push.sample", NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription("temp channel");
            notificationChannel.enableLights(true);
            notificationChannel.enableVibration(true);
            notificationChannel.setLightColor(Color.GREEN);
            notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
            getSystemService(NotificationManager.class).createNotificationChannel(notificationChannel);
        }
    }

}