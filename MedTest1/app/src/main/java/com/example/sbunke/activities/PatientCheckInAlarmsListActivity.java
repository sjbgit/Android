package com.example.sbunke.activities;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sbunke.activities.R;
import com.example.sbunke.adapters.PatientAlarmArrayAdapter;
import com.example.sbunke.adapters.PatientAlarmTimePickerArrayAdapter;
import com.example.sbunke.helpers.SharedPreferencesHelper;
import com.example.sbunke.models.Patient;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PatientCheckInAlarmsListActivity extends Activity {

    private List<Date> dates;
    private PatientAlarmTimePickerArrayAdapter adapter;
    SharedPreferencesHelper helper;

    //public static final String USER_ALARMS = "USER_ALARMS";
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_check_in_alarms_list);

        sharedPreferences = getPreferences(Activity.MODE_PRIVATE);
        helper = new SharedPreferencesHelper(sharedPreferences);

        this.dates = getAlarmDatesFromPreferences();

        adapter = new PatientAlarmTimePickerArrayAdapter(this, dates);

        initializeList();

    }

    private List<Date> getAlarmDatesFromPreferences() {
        //set default if there are none - patient's first time use

        List<Date> alarmDateTimes = helper.getAlarmTimesFromPreferences();





        int[] numbers = {8,12,16,20};

        List<Date> dates = new ArrayList<Date>();
        for (int item : numbers){
            Date newDate = new Date();
            newDate.setHours(item);
            newDate.setMinutes(0);
            dates.add(newDate);
            //dates.add();
        }
        return dates;

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
