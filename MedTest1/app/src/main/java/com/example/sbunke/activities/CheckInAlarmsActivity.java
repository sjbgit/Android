package com.example.sbunke.activities;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
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
}
