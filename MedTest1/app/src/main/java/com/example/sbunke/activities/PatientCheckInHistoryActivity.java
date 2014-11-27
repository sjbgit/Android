package com.example.sbunke.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.sbunke.activities.R;
import com.example.sbunke.adapters.PhysicianCheckInArrayAdapter;
import com.example.sbunke.models.Login;
import com.example.sbunke.models.Patient;
import com.example.sbunke.models.PatientCheckIn;
import com.example.sbunke.repositories.PatientRepository;
import com.example.sbunke.services.ServiceHelper;
import com.example.sbunke.viewmodels.PatientCheckInViewModel;
import com.example.sbunke.views.GraphView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class PatientCheckInHistoryActivity extends Activity {

    float values[]={300,400,300};
    PhysicianCheckInArrayAdapter physicianCheckInArrayAdapter;
    List<PatientCheckInViewModel> checkIns;
    private ProgressDialog progressDialog;
    String patientId = "test id";
    LoadListTask task = new LoadListTask();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_check_in_history);
        //setGraph();

        progressDialog = new ProgressDialog(this);

        //ServiceHelper.GetAllCheckInsByPatientId(patientId);

        bindList(patientId);
        task.execute();
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

    private class LoadListTask extends AsyncTask<String, Void, Integer> {

        @Override
        protected void onPreExecute() {

            progressDialog.setTitle("Updating Check-In List...");
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
                checkIns.clear();
                Collection<PatientCheckIn> patientCheckIns = ServiceHelper.GetAllCheckInsByPatientIdSync(Login.PhysicianSelectedPatient.getId());
                Collection<PatientCheckInViewModel> vms = new ArrayList<PatientCheckInViewModel>();
                for (PatientCheckIn pci : patientCheckIns){
                    Date currentDate = new Date(pci.getCheckInDate());
                    PatientCheckInViewModel vm = new PatientCheckInViewModel(currentDate, pci.getMouthPain(), pci.getFoodConsumption());
                    vms.add(vm);
                }

                //Collection<Patient> pats = ServiceHelper.GetPatientsByPhysicianIdSync(Login.Physician.getId());
                checkIns.addAll(vms);
                //patients.addAll(repository.GetAllPatients(ID));
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
                physicianCheckInArrayAdapter.notifyDataSetChanged();
            }
        }
    }
}
