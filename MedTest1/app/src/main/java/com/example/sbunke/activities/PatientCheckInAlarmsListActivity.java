package com.example.sbunke.activities;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.sbunke.activities.R;
import com.example.sbunke.adapters.PatientAlarmArrayAdapter;
import com.example.sbunke.adapters.PatientAlarmTimePickerArrayAdapter;
import com.example.sbunke.broadcastreceivers.AlarmReceiver;
import com.example.sbunke.helpers.SharedPreferencesHelper;
import com.example.sbunke.models.Patient;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PatientCheckInAlarmsListActivity extends Activity {

    private List<Date> dates;
    private PatientAlarmTimePickerArrayAdapter adapter;
    SharedPreferencesHelper helper;
    private PatientCheckInAlarmsListActivity activity;
    private Context ctx;

    //public static final String USER_ALARMS = "USER_ALARMS";
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_check_in_alarms_list);

        sharedPreferences = getSharedPreferences(SharedPreferencesHelper.USER_ALARMS, Activity.MODE_PRIVATE);
        //sharedPreferences = getPreferences(Activity.MODE_PRIVATE);
        helper = new SharedPreferencesHelper(sharedPreferences);
        //helper.clearUserAlarms();
        this.ctx = this.getBaseContext();
        this.activity = this;

        this.dates = getAlarmDatesFromPreferences();

        adapter = new PatientAlarmTimePickerArrayAdapter(this, dates);

        initializeList();

        initializeButtons();
    }

    private void createAlarms(List<Date> alarmTimes) {

        for (Date date : alarmTimes) {
            setRecurringNotifications(ctx, date);
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



        if (date.before(now.getTime())) {
            //now = now.
            now.setTimeInMillis(now.getTimeInMillis() + oneDay);
        }
        else {
            now.setTime(date);
        }


        //works
        //now.setTime(date);





        //the other approach is to call something else entirely that will handle this
        //manager.set(AlarmManager.RTC_WAKEUP, nextMessage, mAlarmIntent);


        manager.setRepeating(AlarmManager.RTC_WAKEUP,
                now.getTimeInMillis(),
                nextMessage,
                mAlarmIntent);

    }

    private void initializeButtons() {
        ((Button)findViewById(R.id.btnSaveAlarmChanges)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
              //create string and save in preferences

                helper.saveAlarmTimes(activity.dates);
                activity.createAlarms(activity.dates);

                //TODO: CLEAR OUT AND SET ALARMS
                Toast.makeText(activity,
                        "Alarms Saved",
                        Toast.LENGTH_SHORT).show();
            }
        });

        ((Button)findViewById(R.id.btnAddAlarm)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               //add alarm to lis and update adapter
                TimePickerDialog mTimePicker;

                final Date date = new Date();
                mTimePicker = new TimePickerDialog(activity, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {

                        date.setHours(selectedHour);
                        date.setMinutes(selectedMinute);
                        dates.add(date);

                        //Date date = dates.get(pos);

                        //date.setHours(selectedHour);
                        //date.setMinutes(selectedMinute);

                        //viewContainer.tvAlarmTime.setText(date.getHours() + ":" + date.getMinutes());

                        //eReminderTime.setText( selectedHour + ":" + selectedMinute);
                        Toast.makeText(activity,
                                "Selected hour " + selectedHour + " selected minute " + selectedMinute ,
                                Toast.LENGTH_SHORT).show();

                        adapter.notifyDataSetChanged();


                    }
                }, date.getHours(), date.getMinutes(), true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

    }


    private List<Date> getAlarmDatesFromPreferences() {
        //set default if there are none - patient's first time use

        List<Date> alarmDateTimes = helper.getAlarmTimesFromPreferences();

        if (alarmDateTimes == null) {
            alarmDateTimes = new ArrayList<Date>();
        }

        int[] numbers = {8,12,16,20};


        if (alarmDateTimes.size() < numbers.length) {
            int diff = numbers.length - alarmDateTimes.size();
            for (int i = 0; i < diff; i++) {
                Date newDate = new Date();
                newDate.setHours(numbers[i]);
                newDate.setMinutes(0);
                alarmDateTimes.add(newDate);
            }
        }

        return alarmDateTimes;



        //add the number of times necessary


        //if there are fewer than number.length then add the difference to the list
        /*
        int diff = numbers.length - alarmDateTimes.size();
        if (diff > 0) {
            for (int i = 0; i < diff; i++) {
                Date newDate = new Date();
                newDate.setHours(numbers[i]);
                alarmDateTimes.add(newDate);
            }
        }
        */


        //List<Date> dates = new ArrayList<Date>();
        /*
        if (alarmDateTimes.size() == 0) {
            for (int item : numbers){
                Date newDate = new Date();
                newDate.setHours(item);
                newDate.setMinutes(0);
                dates.add(newDate);
                //dates.add();
            }
        }
        */

        /*
        for (Date item : alarmDateTimes) {
            dates.add(item);
        }

        return dates;
        */
    }

    private void initializeList() {

        //long id = 1234;
        //PhysicianRepository repo = new PhysicianRepository();
        //adapter = new PatientArrayAdapter(this, patients);

        //final List<Patient> patients = //repo.GetAllPatients(id);
        //PatientArrayAdapter adapter = new PatientArrayAdapter(this, patients);



        ListView patientListView = (ListView)findViewById(R.id.lvPatientCheckInAlarmsList);
        patientListView.setAdapter(adapter);
        patientListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                int index = arg2;
                Toast.makeText(getBaseContext(),
                        "You have selected item : " + dates.get(index).toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        //registerForContextMenu(patientListView);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.patient_check_in_alarms_list, menu);
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
