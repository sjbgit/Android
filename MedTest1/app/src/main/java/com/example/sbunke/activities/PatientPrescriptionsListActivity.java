package com.example.sbunke.activities;

import android.app.Activity;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sbunke.activities.R;
import com.example.sbunke.adapters.PatientCheckInArrayAdapter;
import com.example.sbunke.adapters.PatientPrescriptionsArrayAdapter;
import com.example.sbunke.models.Login;
import com.example.sbunke.models.Prescription;
import com.example.sbunke.repositories.PatientRepository;
import com.example.sbunke.services.ServiceHelper;
import com.example.sbunke.viewmodels.PrescriptionCheckInViewModel;

import java.util.ArrayList;
import java.util.List;

public class PatientPrescriptionsListActivity extends Activity {

    private List<Prescription> prescriptions;
    private PatientPrescriptionsArrayAdapter adapter;// = new PatientArrayAdapter(this, patients);
    private PatientRepository repository; // = new PhysicianRepository();
    private long ID = -999;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_prescriptions_list);

        //TODO: GET PRESCRIPTIONS FROM PHYSICIAN PATIENT OR LOGIN PATIENT

        prescriptions = new ArrayList<Prescription>();
        repository = new PatientRepository();
        populateList();
        adapter = new PatientPrescriptionsArrayAdapter(this, prescriptions);
        initializeList();

        this.context = this;

        Integer i = 1;

        initializeButtons();

        //PendingIntent.getService(this, 1, i, 0);
    }

    private void initializeButtons() {
         if(Login.Patient != null) {

             Button b1 = (Button)findViewById(R.id.btnAddPrescription);
             b1.setVisibility(View.INVISIBLE);
             Button b2 = (Button)findViewById(R.id.btnSavePrescriptionChanges);
             b2.setVisibility(View.INVISIBLE);

         }

        ((Button)findViewById(R.id.btnAddPrescription)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context);
                // Include dialog.xml file
                dialog.setContentView(R.layout.new_prescription_dialog_layout);
                // Set dialog title
                dialog.setTitle("Add Prescription");

                // set values for custom dialog components - text, image and button

                final TextView medName = (TextView) dialog.findViewById(R.id.etMedicationName);
                final TextView medDosage = (TextView) dialog.findViewById(R.id.etDosage);



                //text.setText("Custom dialog Android example.");
                //ImageView image = (ImageView) dialog.findViewById(R.id.imageDialog);
                //image.setImageResource(R.drawable.image0);

                dialog.show();

                Button declineButton = (Button) dialog.findViewById(R.id.btnCancelAddPrescription);
                // if decline button is clicked, close the custom dialog
                declineButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Close dialog
                        dialog.dismiss();
                    }
                });

                Button addButton = (Button) dialog.findViewById(R.id.btnAddPrescription);
                // if decline button is clicked, close the custom dialog
                addButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Prescription p = new Prescription(medName.getText().toString(), medDosage.getText().toString());
                        prescriptions.add(p);
                        adapter.notifyDataSetChanged();
                        // Close dialog
                        dialog.dismiss();
                    }
                });

            }
        });

        ((Button)findViewById(R.id.btnSavePrescriptionChanges)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                ServiceHelper.UpdatePatientPrescriptionsAsync(Login.PhysicianSelectedPatient.getId(), prescriptions);

                Toast.makeText(getBaseContext(),
                        "Prescription Changes Saved",
                        Toast.LENGTH_SHORT).show();

            }
        });
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
