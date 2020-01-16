package com.example.testapplication;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class NotificationActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnNotify, btnCancel, btnUpdate;
    private String CHANNEL_id = "CUSTOM_CHANNEL";
    private String ACTION_UPDATE = "ACTION_UPDATE";
    private int NOTIFICATION_ID = 1;
    private NotificationManager notifyMgr;
    private NotificationReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        createNotificationChannel();
        receiver = new NotificationReceiver();
        registerReceiver(receiver, new IntentFilter(ACTION_UPDATE));

        btnNotify = (Button) findViewById(R.id.notify);
        btnNotify.setOnClickListener(this);
        btnCancel = (Button) findViewById(R.id.cancel);
        btnCancel.setOnClickListener(this);
        btnUpdate = (Button) findViewById(R.id.update);
        btnUpdate.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.notify:
                sendNotification();
                break;
            case R.id.cancel:
                cancelNotification();
                break;
            case R.id.update:
                updateNotification();
                break;
        }
    }

    private void updateNotification() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mascot_1);
        NotificationCompat.Builder builder = getNotificationBuilder();
        builder.setStyle(new NotificationCompat.BigPictureStyle()
                .bigPicture(bitmap)
                .setBigContentTitle("Big Content Title"));
        notifyMgr.notify(NOTIFICATION_ID, builder.build());
    }

    private void cancelNotification() {
        notifyMgr.cancel(NOTIFICATION_ID);
    }

    private void sendNotification() {
        notifyMgr.notify(NOTIFICATION_ID, getNotificationBuilder().build());
    }

    private NotificationCompat.Builder getNotificationBuilder() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_id);
        builder.setContentTitle("You have been notified");
        builder.setContentText("Sample text from Notification");
        builder.setSmallIcon(R.drawable.ic_launcher_foreground);
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setAutoCancel(true);
        Intent intent = new Intent(this, NotificationActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, NOTIFICATION_ID, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        Intent actionIntent = new Intent(ACTION_UPDATE);
        PendingIntent notifPendingIntent = PendingIntent.getBroadcast(this, NOTIFICATION_ID, actionIntent, PendingIntent.FLAG_ONE_SHOT);
        builder.addAction(R.drawable.ic_launcher_foreground, "Click Action", notifPendingIntent);
        return builder;
    }

    private void createNotificationChannel() {
        notifyMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_id, "Notify Me", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription("Notification from a Channel");
            notifyMgr.createNotificationChannel(notificationChannel);
        }
    }

    class NotificationReceiver extends BroadcastReceiver {

        NotificationReceiver() {

        }

        @Override
        public void onReceive(Context context, Intent intent) {
            updateNotification();
        }
    }
}