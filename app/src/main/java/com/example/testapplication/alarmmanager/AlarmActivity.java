package com.example.testapplication.alarmmanager;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.testapplication.R;

public class AlarmActivity extends AppCompatActivity {

    private static final int NOTIFICATION_ID = 0;
    private static final String PRIMARY_CHANNEL_ID =
            "primary_notification_channel";
    NotificationManager notificationManager;
    private ToggleButton alarmToggle;
    private AlarmManager alarmManager;
    private Intent notifyIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        createNotificationChannel();
        //Create the Pending Intent for the Alarm to go off


        notifyIntent = new Intent(this, StandUpReceiver.class);
        final PendingIntent notifyPendingIntent = PendingIntent.getBroadcast(this, NOTIFICATION_ID, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        //Create the Alarm Manager
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        alarmToggle = findViewById(R.id.alarmToggle);


        alarmToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String toastMesage;
                if (isChecked) {
                    //deliverNotification(AlarmActivity.this);
                    alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime(), AlarmManager.INTERVAL_FIFTEEN_MINUTES, notifyPendingIntent);

                    toastMesage = "Stand up alarm ON!";
                } else {
                    alarmManager.cancel(notifyPendingIntent);
                    toastMesage = "Stand up alarm OFF";
                    notificationManager.cancelAll();
                }
                Toast.makeText(AlarmActivity.this, toastMesage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        boolean alarmUp = PendingIntent.getBroadcast(this, NOTIFICATION_ID, notifyIntent, PendingIntent.FLAG_NO_CREATE) != null;
        if (alarmUp)
            alarmToggle.setChecked(true);
    }

    private void createNotificationChannel() {

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(PRIMARY_CHANNEL_ID, "Alarm Notification", NotificationManager.IMPORTANCE_LOW);
            channel.enableVibration(true);
            channel.enableLights(true);
            notificationManager.createNotificationChannel(channel);
        }

    }


}