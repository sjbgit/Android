package com.example.sbunke.broadcastreceivers;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.sbunke.helpers.NotificationHelper;
import com.example.sbunke.helpers.SharedPreferencesHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class RetrieveDataBroadcastReceiver extends BroadcastReceiver {

    //private Context ctx;
    public RetrieveDataBroadcastReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //throw new UnsupportedOperationException("Not yet implemented");

        //ctx = context;
        Calendar now = Calendar.getInstance();
        DateFormat formatter = SimpleDateFormat.getTimeInstance();
        Toast.makeText(context, "This is from the Med DATE RETRIEVE receiver " + formatter.format(now.getTime()),
                Toast.LENGTH_SHORT).show();

        //NotificationHelper.showNotification(context);


        //TODO - READ PATIENT ENTERED ALARM TIMES FOR CHECK-IN REMINDERS
        //GET USER PREFERENCES, IF PHYSICIAN..., IF PATIENT...


        //NOTE - UNCOMMENT THIS TO GET BOOT BASED RECURRING NOTIFICATIONS TO WORK
        //setRecurringNotifications(context);
        getSharedPreferencesDates(context);
    }

    private void getSharedPreferencesDates(Context context) {
        //sharedPreferences = context.getPreferences(Activity.MODE_PRIVATE);
        SharedPreferences prefs = context.getSharedPreferences(SharedPreferencesHelper.USER_ALARMS, Activity.MODE_PRIVATE);
        SharedPreferencesHelper helper = new SharedPreferencesHelper(prefs);
        List<Date> dates = helper.getAlarmTimesFromPreferences();


        /*
        Toast.makeText(context,
                "Length of list is: " + dates.size(),
                Toast.LENGTH_SHORT).show();
        */
        for (Date date : dates) {
            Toast.makeText(context,
                    "Set notification for: " + date,
                    Toast.LENGTH_SHORT).show();
            setRecurringNotifications(context,date);
        }


    }

    private void setRecurringNotifications(Context context, Date date) {
        AlarmManager manager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent launchIntent = new Intent(context, AlarmReceiver.class);
        PendingIntent mAlarmIntent = PendingIntent.getBroadcast(context, 0, launchIntent, 0);
        long oneDay = 24*3600*1000;
        //long nextMessage = 30000;
        long nextMessage = 1000 * 60 * 5;
        Calendar now = Calendar.getInstance();

        now.setTime(date);


        //the other approach is to call something else entirely that will handle this
        //manager.set(AlarmManager.RTC_WAKEUP, nextMessage, mAlarmIntent);

        manager.setRepeating(AlarmManager.RTC_WAKEUP,
                now.getTimeInMillis(),
                nextMessage,
                mAlarmIntent);

        /*
        manager.setRepeating(AlarmManager.RTC_WAKEUP,
                now.getTimeInMillis(),
                nextMessage,
                mAlarmIntent);
        */
    }


    private void setRecurringNotifications(Context context) {
        AlarmManager manager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent launchIntent = new Intent(context, AlarmReceiver.class);
        PendingIntent mAlarmIntent = PendingIntent.getBroadcast(context, 0, launchIntent, 0);
        long oneDay = 24*3600*1000;
        //long nextMessage = 30000;
        long nextMessage = 1000 * 60 * 5;
        Calendar now = Calendar.getInstance();


        //the other approach is to call something else entirely that will handle this
        //manager.set(AlarmManager.RTC_WAKEUP, nextMessage, mAlarmIntent);

        manager.setRepeating(AlarmManager.RTC_WAKEUP,
                now.getTimeInMillis(),
                nextMessage,
                mAlarmIntent);

        /*
        manager.setRepeating(AlarmManager.RTC_WAKEUP,
                now.getTimeInMillis(),
                nextMessage,
                mAlarmIntent);
        */
    }
}
