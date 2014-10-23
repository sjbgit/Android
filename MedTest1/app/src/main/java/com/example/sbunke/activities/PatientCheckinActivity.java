package com.example.sbunke.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import com.example.sbunke.activities.R;
import com.example.sbunke.adapters.PatientArrayAdapter;
import com.example.sbunke.adapters.PatientCheckInArrayAdapter;
import com.example.sbunke.models.Patient;
import com.example.sbunke.repositories.PatientRepository;
import com.example.sbunke.repositories.PhysicianRepository;
import com.example.sbunke.viewmodels.PrescriptionCheckInViewModel;

import java.util.ArrayList;
import java.util.List;

public class PatientCheckInActivity extends Activity {

    private ProgressDialog progressDialog;
    private List<PrescriptionCheckInViewModel> prescriptionCheckInViewModels;// = new ArrayList<Patient>();
    private PatientCheckInArrayAdapter adapter;// = new PatientArrayAdapter(this, patients);
    private PatientRepository repository; // = new PhysicianRepository();
    //TODO: GET FROM INTENT
    private long ID = -999;//THIS WILL COME FROM THE PASSED IN INTENT
    //private LoadListTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_checkin);
        prescriptionCheckInViewModels = new ArrayList<PrescriptionCheckInViewModel>();
        repository = new PatientRepository();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.patient_checkin, menu);
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
/*
    private class LoadListTask extends AsyncTask<String, Void, Integer> {

        @Override
        protected void onPreExecute() {

            progressDialog.setTitle("Updating Patient List...");
            progressDialog.setMessage("Please wait.");
            progressDialog.setCancelable(false);
            progressDialog.setIndeterminate(true);
            progressDialog.show();
        }

        @Override
        protected Integer doInBackground(String... params) {

            Integer result = 0;

            try {
                //Do something...
                Thread.sleep(1000);
                //patients = repository.GetAllPatients(ID);
                patients.clear();
                patients.addAll(repository.GetAllPatients(ID));
                //adapter.notifyDataSetChanged();
                result = 1;
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return result;

            //patients = repository.GetAllPatients(ID);
            //for () {
            //    listItems.add(something to add);
            //}

        }

        @Override
        protected void onPostExecute(Integer result) {
            if (progressDialog!=null) {
                progressDialog.dismiss();
                //TODO: ENABLE WHEN DONE LOADING
                //b.setEnabled(true);
            }

            //progressDialog.dismiss();
            if (result == 1) {
                adapter.notifyDataSetChanged();
            }
        }
    }
    */
}
