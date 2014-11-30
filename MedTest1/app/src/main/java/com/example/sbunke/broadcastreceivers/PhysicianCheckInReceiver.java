package com.example.sbunke.broadcastreceivers;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.sbunke.models.CheckIn;

import java.util.Calendar;

public class PhysicianCheckInReceiver extends BroadcastReceiver {
    public PhysicianCheckInReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //throw new UnsupportedOperationException("Not yet implemented");

        //TODO: UNCOMMENT THIS FOR PHYSICIAN ALARMS TO WORK
        //SetRecurringPatientCheckInAlarm(context);
    }

    private void SetRecurringPatientCheckInAlarm(Context context) {
        AlarmManager manager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent launchIntent = new Intent(context, CheckInReceiver.class);
        PendingIntent mAlarmIntent = PendingIntent.getBroadcast(context, 0, launchIntent, 0);
        long oneDay = 24*3600*1000;
        //long nextMessage = 30000;
        long nextMessage = 1000 * 60 * 2;
        Calendar now = Calendar.getInstance();

        //the other approach is to call something else entirely that will handle this
        //manager.set(AlarmManager.RTC_WAKEUP, nextMessage, mAlarmIntent);

        manager.setRepeating(AlarmManager.RTC_WAKEUP,
                now.getTimeInMillis(),
                nextMessage,
                mAlarmIntent);
    }
}
