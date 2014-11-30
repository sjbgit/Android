package com.example.sbunke.broadcastreceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.sbunke.helpers.NotificationHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CheckInReceiver extends BroadcastReceiver {
    public CheckInReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        //throw new UnsupportedOperationException("Not yet implemented");

        Calendar now = Calendar.getInstance();
        DateFormat formatter = SimpleDateFormat.getTimeInstance();
        Toast.makeText(context, "PHYSICIAN CHECK-IN RECEIVER " + formatter.format(now.getTime()),
                Toast.LENGTH_SHORT).show();

        NotificationHelper.showPhysicianCheckInAlertNotification(context);
    }
}
