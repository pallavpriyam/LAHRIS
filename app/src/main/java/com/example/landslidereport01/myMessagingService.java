package com.example.landslidereport01;

import android.app.NotificationManager;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class myMessagingService extends FirebaseMessagingService {
    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        Toast.makeText(myMessagingService.this,"NEW REVIEW",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        String notificationTitle = null;
        String notificationBody = null;
        if (remoteMessage.getNotification() != null) {
            notificationTitle = remoteMessage.getNotification().getTitle();
            notificationBody = remoteMessage.getNotification().getBody();
        }

        showNotification(notificationTitle, notificationBody);

    }

    public void showNotification(String title, String message)
    {
        NotificationCompat.Builder builder= new NotificationCompat.Builder(this,"mn")
                .setContentTitle(title)
                .setSmallIcon(R.drawable.maps_icon)
                .setAutoCancel(true)
                .setContentText(message);

        NotificationManager managerCompat = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        managerCompat.notify(0,builder.build());
    }
}
