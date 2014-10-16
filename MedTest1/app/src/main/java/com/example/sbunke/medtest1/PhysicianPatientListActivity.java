package com.example.sbunke.medtest1;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sbunke.adapters.PatientArrayAdapter;
import com.example.sbunke.dataaccess.PhysicianRepository;
import com.example.sbunke.models.Patient;

import java.util.List;


public class PhysicianPatientListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physician_patient_list);
        InitializeList();
    }


    private void InitializeList() {

        long id = 1234;
        PhysicianRepository repo = new PhysicianRepository();
        final List<Patient> patients = repo.GetAllPatients(id);
        PatientArrayAdapter paa = new PatientArrayAdapter(this, patients);

        ListView patientListView = (ListView)findViewById(R.id.physicianPatientListView);
        patientListView.setAdapter(paa);
        patientListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                int index = arg2;
                Toast.makeText(getBaseContext(),
                        "You have selected item : " + patients.get(index).getFirstName(),
                        Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.physician_patient_list, menu);
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
