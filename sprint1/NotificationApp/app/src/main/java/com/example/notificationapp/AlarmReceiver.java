package com.example.notificationapp;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import static android.provider.Settings.System.getString;

public class AlarmReceiver extends BroadcastReceiver {
    private static final String CHANNEL_ID = "SAMPLE_CHANNEL";


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onReceive(Context context, Intent intent) {
        // get id and message from intent
        int notificationId = intent.getIntExtra("notificationId", 0);
        String message = intent.getStringExtra("Reminder");

        // call MainActivity when notification is pressed
        Intent mainIntent = new Intent(context, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, mainIntent, 0);

        // notification manager
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        int api = Build.VERSION_CODES.O;
        if (Build.VERSION.SDK_INT >= api) {
            //For API 26 and above
            CharSequence channel_name = "My Notification";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, channel_name, importance);
            notificationManager.createNotificationChannel(channel);
        }
        // prepare notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID).setSmallIcon(android.R.drawable.ic_dialog_info).setContentTitle("Reminder:").setContentText(message).setContentIntent(contentIntent).setPriority(NotificationCompat.PRIORITY_DEFAULT).setAutoCancel(true);
        // notify
        notificationManager.notify(notificationId, builder.build());




    }
}
