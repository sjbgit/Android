package com.example.sbunke.activities;

import android.app.Activity;
import android.app.PendingIntent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sbunke.activities.R;
import com.example.sbunke.adapters.PatientCheckInArrayAdapter;
import com.example.sbunke.adapters.PatientPrescriptionsArrayAdapter;
import com.example.sbunke.models.Prescription;
import com.example.sbunke.repositories.PatientRepository;
import com.example.sbunke.viewmodels.PrescriptionCheckInViewModel;

import java.util.ArrayList;
import java.util.List;

public class PatientPrescriptionsListActivity extends Activity {

    private List<Prescription> prescriptions;
    private PatientPrescriptionsArrayAdapter adapter;// = new PatientArrayAdapter(this, patients);
    private PatientRepository repository; // = new PhysicianRepository();
    private long ID = -999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_prescriptions_list);

        prescriptions = new ArrayList<Prescription>();
        repository = new PatientRepository();
        populateList();
        adapter = new PatientPrescriptionsArrayAdapter(this, prescriptions);
        initializeList();

        Integer i = 1;

        //PendingIntent.getService(this, 1, i, 0);
    }


    private void populateList() {
        prescriptions = repository.GetAllPrescriptionsForPatient(ID);
    }

    private void initializeList() {

        //long id = 1234;
        //PhysicianRepository repo = new PhysicianRepository();
        //adapter = new PatientArrayAdapter(this, patients);

        //final List<Patient> patients = //repo.GetAllPatients(id);
        //PatientArrayAdapter adapter = new PatientArrayAdapter(this, patients);



        ListView patientListView = (ListView)findViewById(R.id.lvPatientPrescriptions);
        patientListView.setAdapter(adapter);
        patientListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                int index = arg2;
                Toast.makeText(getBaseContext(),
                        "You have selected item : " + prescriptions.get(index).getName(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        registerForContextMenu(patientListView);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.patient_prescriptions_list, menu);
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
