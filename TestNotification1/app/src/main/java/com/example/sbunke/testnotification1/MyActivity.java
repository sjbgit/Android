package com.example.sbunke.testnotification1;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.NotificationCompat.WearableExtender;


public class MyActivity extends Activity {

    Button start, clear;
    Notification noti;
    NotificationManager nmgr;
    public static final int NOTIFICATION_ID = 0;
    Activity mActivity;

    public static String EXTRA_EVENT_ID = "extraid";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        mActivity = this;
        //(Button)findViewById(R.layout.n)

        ((Button)findViewById(R.id.notifyButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int notificationId = 001;
// Build intent for notification content
                Intent viewIntent = new Intent(mActivity, ViewEventActivity.class);
                viewIntent.putExtra(EXTRA_EVENT_ID, 1000);
                PendingIntent viewPendingIntent =
                        PendingIntent.getActivity(mActivity, 0, viewIntent, 0);

                NotificationCompat.Builder notificationBuilder =
                        new NotificationCompat.Builder(mActivity)
                                //.setSmallIcon(R.drawable.ic_event)
                                .setContentTitle("Test Title")
                                .setContentText("Test Context")
                                .setContentIntent(viewPendingIntent);

// Get an instance of the NotificationManager service
                NotificationManagerCompat notificationManager =
                        NotificationManagerCompat.from(mActivity);

// Build the notification and issues it with notification manager.
                notificationManager.notify(notificationId, notificationBuilder.build());
            }
        });

    }

    public void Notification() {


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
