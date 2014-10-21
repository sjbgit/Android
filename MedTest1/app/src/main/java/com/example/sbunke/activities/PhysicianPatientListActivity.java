package com.example.sbunke.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sbunke.adapters.PatientArrayAdapter;
import com.example.sbunke.models.Physician;
import com.example.sbunke.repositories.PhysicianRepository;
import com.example.sbunke.models.Patient;

import java.util.ArrayList;
import java.util.List;


public class PhysicianPatientListActivity extends Activity {

    private ProgressDialog progressDialog;
    private List<Patient> patients = new ArrayList<Patient>();
    private PatientArrayAdapter adapter = new PatientArrayAdapter(this, patients);
    private PhysicianRepository repository = new PhysicianRepository();
    //TODO: GET FROM INTENT
    private long ID = -999;//THIS WILL COME FROM THE PASSED IN INTENT

    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physician_patient_list);
        InitializeList();
    }


    private void InitializeList() {

        //long id = 1234;
        //PhysicianRepository repo = new PhysicianRepository();
        //adapter = new PatientArrayAdapter(this, patients);

        //final List<Patient> patients = //repo.GetAllPatients(id);
        //PatientArrayAdapter adapter = new PatientArrayAdapter(this, patients);

        LoadListTask task = new LoadListTask();
        task.doInBackground();

        ListView patientListView = (ListView)findViewById(R.id.physicianPatientListView);
        patientListView.setAdapter(adapter);
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

    private class LoadListTask extends AsyncTask<String, Void, Integer> {
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(context);
            progressDialog.setTitle("Processing...");
            progressDialog.setMessage("Please wait.");
            progressDialog.setCancelable(false);
            progressDialog.setIndeterminate(true);
            progressDialog.show();
        }

        protected Integer doInBackground(String... params) {

            try {
                //Do something...
                Thread.sleep(5000);
                patients = repository.GetAllPatients(ID);
                return 0;
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return 1;

            //patients = repository.GetAllPatients(ID);
            //for () {
            //    listItems.add(something to add);
            //}

        }

        protected void onPostExecute(Integer result) {
            if (progressDialog!=null) {
                progressDialog.dismiss();
                //TODO: ENABLE WHEN DONE LOADING
                //b.setEnabled(true);
            }

            //progressDialog.dismiss();
            if (result == 0) {
                adapter.notifyDataSetChanged();
            }
        }
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

//http://www.techrepublic.com/blog/software-engineer/androids-indeterminate-progressdialog-tutorial/
/*
    @Override
    public void onClick(View v) {
        v.setEnabled(false);
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {

            @Override
            protected void onPreExecute() {
                pd = new ProgressDialog(context);
                pd.setTitle("Processing...");
                pd.setMessage("Please wait.");
                pd.setCancelable(false);
                pd.setIndeterminate(true);
                pd.show();
            }

            @Override
            protected Void doInBackground(Void... arg0) {
                try {
                    //Do something...
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                if (pd!=null) {
                    pd.dismiss();
                    b.setEnabled(true);
                }
            }

        };
        task.execute((Void[])null);
    }
*/