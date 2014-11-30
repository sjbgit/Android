package com.example.sbunke.broadcastreceivers;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.sbunke.activities.CheckInAlarmsActivity;
import com.example.sbunke.activities.PatientCheckInActivity;
import com.example.sbunke.helpers.NotificationHelper;
import com.example.sbunke.helpers.SharedPreferencesHelper;
import com.example.sbunke.models.UserCredentials;
import com.example.sbunke.models.UserType;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AlarmReceiver extends BroadcastReceiver {
    public AlarmReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //throw new UnsupportedOperationException("Not yet implemented");

        Calendar now = Calendar.getInstance();
        DateFormat formatter = SimpleDateFormat.getTimeInstance();
        Toast.makeText(context, "This is from the Med receiver " + formatter.format(now.getTime()),
                Toast.LENGTH_SHORT).show();


        UserCredentials credentials = SharedPreferencesHelper.GetCredentialsFromPreferences(context);
        if (credentials.getUserType().equals(UserType.PHYSICIAN)) {

        }

        NotificationHelper.showNotification(context);


    }

    public void showNotification() {

    }

    //http://stackoverflow.com/questions/12924835/android-notification-from-broadcastreceiver

}
