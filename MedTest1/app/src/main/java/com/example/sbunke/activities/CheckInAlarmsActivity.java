package com.example.sbunke.activities;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import com.example.sbunke.activities.R;
import com.example.sbunke.broadcastreceivers.AlarmReceiver;

import java.util.Calendar;

public class CheckInAlarmsActivity extends Activity {

    private Context ctx;
    private PendingIntent mAlarmIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in_alarms);

        ctx = this;

        ((Button)findViewById(R.id.btnUpdateAlarm)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                //Intent launchIntent =
                //        new Intent(ctx, PatientMainActivity.class);
                //PendingIntent contentIntent =
                //        PendingIntent.getActivity(ctx, 0, launchIntent, 0);
                //ctx.startActivity(launchIntent);

                TimePicker tp1 = (TimePicker)findViewById(R.id.tpCheckin1);
                int hour = tp1.getCurrentHour();
                int min = tp1.getCurrentMinute();
                String time =hour+":"+min;


                Intent launchIntent = new Intent(ctx, AlarmReceiver.class);
                mAlarmIntent = PendingIntent.getBroadcast(ctx, 0, launchIntent, 0);

                long alarmTime = getMilliseconds(hour, min);


                try {
                    AlarmManager manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
                    //manager.setTime(alarmTime);
                    manager.set(AlarmManager.RTC_WAKEUP, alarmTime, mAlarmIntent);
                }
                catch (Exception ex) {
                    String message = ex.getMessage();
                }


            }
        });
    }

    //EXCELLENT - MUST DO - USE DIALOG
    //http://android-er.blogspot.com/2013/06/set-alarm-on-specified-datetime-with.html

    //http://android-er.blogspot.com/2012/05/create-alarm-set-on-specified-time.html
    private long getMilliseconds(int hourOfDay, int minute) {

        Calendar calNow = Calendar.getInstance();
        Calendar calSet = (Calendar) calNow.clone();

        calSet.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calSet.set(Calendar.MINUTE, minute);
        calSet.set(Calendar.SECOND, 0);
        calSet.set(Calendar.MILLISECOND, 0);

        /* SET TO TOMORROW
        if(calSet.compareTo(calNow) <= 0){
            //Today Set time passed, count to tomorrow
            calSet.add(Calendar.DATE, 1);
        }
        */

        return calSet.getTimeInMillis();

        //setAlarm(calSet);
    }
//http://stackoverflow.com/questions/11237920/which-permissions-an-android-application-need-in-order-to-use-the-alarm-manager


@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.check_in_alarms, menu);
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

    @Override
    public void onStop() {
        super.onStop();

        //showNotification(this);
        /*
        NotificationManager manager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        //Post 4 unique notifications
        Notification note = buildNotification(NOTE_BASIC);
        manager.notify(NOTE_BASIC, note);
        note = buildNotification(NOTE_BIGTEXT);
        manager.notify(NOTE_BIGTEXT, note);
        note = buildNotification(NOTE_PICTURE);
        manager.notify(NOTE_PICTURE, note);
        note = buildNotification(NOTE_INBOX);
        manager.notify(NOTE_INBOX, note);

        */
    }

    private Notification buildNotification(int type) {
        Intent launchIntent =
                new Intent(this, PatientCheckInActivity.class);
        PendingIntent contentIntent =
                PendingIntent.getActivity(this, 0, launchIntent, 0);


        // Create notification using a builder
        Notification.Builder builder = new Notification.Builder(
                CheckInAlarmsActivity.this);


        builder.setSmallIcon(R.drawable.ic_launcher)
                .setTicker("Something Happened")
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setContentTitle("We're Finished!")
                .setContentText("Click Here!")
                .setContentIntent(contentIntent);

        switch (type) {
            case NOTE_BASIC:
                //Return the simple notification
                return builder.build();
            case NOTE_BIGTEXT:
                //Include two actions
                builder.addAction(android.R.drawable.ic_menu_call,
                        "Call", contentIntent);
                builder.addAction(android.R.drawable.ic_menu_recent_history,
                        "History", contentIntent);
                //Use the BigTextStyle when expanded
                Notification.BigTextStyle textStyle =
                        new Notification.BigTextStyle(builder);
                textStyle.bigText(
                        "Here is some additional text to be displayed when the notification is "
                                +"in expanded mode.  I can fit so much more content into this giant view!");

                return textStyle.build();
            case NOTE_PICTURE:
                //Add one additional action
                builder.addAction(android.R.drawable.ic_menu_compass,
                        "View Location", contentIntent);
                //Use the BigPictureStyle when expanded
                Notification.BigPictureStyle pictureStyle =
                        new Notification.BigPictureStyle(builder);
                pictureStyle.bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher));

                return pictureStyle.build();
            case NOTE_INBOX:
                //Use the InboxStyle when expanded
                Notification.InboxStyle inboxStyle =
                        new Notification.InboxStyle(builder);
                inboxStyle.setSummaryText("4 New Tasks");
                inboxStyle.addLine("Make Dinner");
                inboxStyle.addLine("Call Mom");
                inboxStyle.addLine("Call Wife First");
                inboxStyle.addLine("Pick up Kids");

                return inboxStyle.build();
            default:
                throw new IllegalArgumentException("Unknown Type");
        }
    }

    public static void showNotification(Context context) {
        NotificationManager manager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        //Post 4 unique notifications
        Notification note = buildNotification(NOTE_BASIC, context);
        manager.notify(NOTE_BASIC, note);
        note = buildNotification(NOTE_BIGTEXT, context);
        manager.notify(NOTE_BIGTEXT, note);
        note = buildNotification(NOTE_PICTURE, context);
        manager.notify(NOTE_PICTURE, note);
        note = buildNotification(NOTE_INBOX, context);
        manager.notify(NOTE_INBOX, note);
    }

    public static Notification buildNotification(int type, Context context) {
        Intent launchIntent =
                new Intent(context, PatientCheckInActivity.class);
        PendingIntent contentIntent =
                PendingIntent.getActivity(context, 0, launchIntent, 0);


        // Create notification using a builder
        Notification.Builder builder = new Notification.Builder(
                context);


        builder.setSmallIcon(R.drawable.ic_launcher)
                .setTicker("Something Happened")
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setContentTitle("We're Finished!")
                .setContentText("Click Here!")
                .setContentIntent(contentIntent);

        switch (type) {
            case NOTE_BASIC:
                //Return the simple notification
                return builder.build();
            case NOTE_BIGTEXT:
                //Include two actions
                builder.addAction(android.R.drawable.ic_menu_call,
                        "Call", contentIntent);
                builder.addAction(android.R.drawable.ic_menu_recent_history,
                        "History", contentIntent);
                //Use the BigTextStyle when expanded
                Notification.BigTextStyle textStyle =
                        new Notification.BigTextStyle(builder);
                textStyle.bigText(
                        "Here is some additional text to be displayed when the notification is "
                                +"in expanded mode.  I can fit so much more content into this giant view!");

                return textStyle.build();
            case NOTE_PICTURE:
                //Add one additional action
                builder.addAction(android.R.drawable.ic_menu_compass,
                        "View Location", contentIntent);
                //Use the BigPictureStyle when expanded
                Notification.BigPictureStyle pictureStyle =
                        new Notification.BigPictureStyle(builder);
                pictureStyle.bigPicture(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher));

                return pictureStyle.build();
            case NOTE_INBOX:
                //Use the InboxStyle when expanded
                Notification.InboxStyle inboxStyle =
                        new Notification.InboxStyle(builder);
                inboxStyle.setSummaryText("4 New Tasks");
                inboxStyle.addLine("Make Dinner");
                inboxStyle.addLine("Call Mom");
                inboxStyle.addLine("Call Wife First");
                inboxStyle.addLine("Pick up Kids");

                return inboxStyle.build();
            default:
                throw new IllegalArgumentException("Unknown Type");
        }
    }

    public static final int NOTE_BASIC = 100;
    public static final int NOTE_BIGTEXT = 200;
    public static final int NOTE_PICTURE = 300;
    public static final int NOTE_INBOX = 400;
}
