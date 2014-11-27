package com.example.sbunke.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.sbunke.activities.R;
import com.example.sbunke.adapters.PhysicianCheckInArrayAdapter;
import com.example.sbunke.models.Patient;
import com.example.sbunke.repositories.PatientRepository;
import com.example.sbunke.services.ServiceHelper;
import com.example.sbunke.viewmodels.PatientCheckInViewModel;
import com.example.sbunke.views.GraphView;

import java.util.ArrayList;
import java.util.List;

public class PatientCheckInHistoryActivity extends Activity {

    float values[]={300,400,300};
    PhysicianCheckInArrayAdapter physicianCheckInArrayAdapter;
    List<PatientCheckInViewModel> checkIns;

    String patientId = "test id";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_check_in_history);
        //setGraph();

        ServiceHelper.GetAllCheckInsByPatientId(patientId);

        bindList(patientId);
    }

    private void bindList(String patientId) {
        PatientRepository repo = new PatientRepository();
        checkIns = repo.getAllCheckInsForPatient(patientId);
        physicianCheckInArrayAdapter = new PhysicianCheckInArrayAdapter(this, checkIns);
        //physicianCheckInArrayAdapter.notifyDataSetChanged();

        ListView patientListView = (ListView)findViewById(R.id.lvPhysicianCheckIns);
        patientListView.setAdapter(physicianCheckInArrayAdapter);

    }

    private void setGraph() {
        LinearLayout linear =(LinearLayout)findViewById(R.id.llPatientCheckInHistory);
        values=calculateData(values);
        linear.addView(new GraphView(this, values));
    }

    private float[] calculateData(float[] data) {

        float total=0;
        for(int i=0;i<data.length;i++)
        {
            total+=data[i];
        }
        for(int i=0;i<data.length;i++)
        {
            data[i]=360*(data[i]/total);
        }
        return data;

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.patient_check_in_history, menu);
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
