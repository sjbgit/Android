package com.example.sbunke.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.sbunke.activities.R;
import com.example.sbunke.adapters.PatientArrayAdapter;
import com.example.sbunke.adapters.PatientCheckInArrayAdapter;
import com.example.sbunke.models.CheckIn;
import com.example.sbunke.models.Login;
import com.example.sbunke.models.Patient;
import com.example.sbunke.models.Prescription;
import com.example.sbunke.repositories.PatientRepository;
import com.example.sbunke.repositories.PhysicianRepository;
import com.example.sbunke.services.ServiceHelper;
import com.example.sbunke.viewmodels.PrescriptionCheckInViewModel;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PatientCheckInActivity extends Activity {

    private ProgressDialog progressDialog;
    private List<PrescriptionCheckInViewModel> prescriptionCheckInViewModels;// = new ArrayList<Patient>();
    private PatientCheckInArrayAdapter adapter;// = new PatientArrayAdapter(this, patients);
    private PatientRepository repository; // = new PhysicianRepository();
    //TODO: GET FROM INTENT
    private long ID = -999;//THIS WILL COME FROM THE PASSED IN INTENT
    //private LoadListTask task;
    private Context context;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageView imageView;
    String mCurrentPhotoPath;
    static final int REQUEST_TAKE_PHOTO = 1;
    final static int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1;
    Uri imageUri = null;
    CheckIn checkIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_checkin);
        prescriptionCheckInViewModels = new ArrayList<PrescriptionCheckInViewModel>();
        repository = new PatientRepository();
        populateViewModel();

        context = this;
        adapter = new PatientCheckInArrayAdapter(this, prescriptionCheckInViewModels);

        initializeList();

        setRadioButtonListeners();
        bindPictureCaptureButton();
        bindSaveCheckInButton();
        imageView = (ImageView)findViewById(R.id.ivCheckInPic);

        checkIn = new CheckIn(Login.Patient);

    }

    private void bindSaveCheckInButton() {
        //btnSaveCheckIn
        ((Button)findViewById(R.id.btnSaveCheckIn)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(context, "Check-In Saved", Toast.LENGTH_SHORT).show();
                //(new PatientRepository()).SaveCheckIn();

                checkIn.setPrescriptionCheckInViewModelCollection(prescriptionCheckInViewModels);

                ServiceHelper.AddCheckIn(checkIn);

                Integer x = 1;

            }
        });
    }

    private void capturePic() {
        // Define the file-name to save photo taken by Camera activity

        String fileName = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        //String fileName = "Camera_Example.jpg";

        // Create parameters for Intent with filename

        ContentValues values = new ContentValues();

        values.put(MediaStore.Images.Media.TITLE, fileName);

        values.put(MediaStore.Images.Media.DESCRIPTION,"Image capture by camera");

        // imageUri is the current activity attribute, define and save it for later usage

        imageUri = getContentResolver().insert(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

        /**** EXTERNAL_CONTENT_URI : style URI for the "primary" external storage volume. ****/


        // Standard Intent action that can be sent to have the camera
        // application capture an image and return it.

        Intent intent = new Intent( MediaStore.ACTION_IMAGE_CAPTURE );

        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);

        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);

        startActivityForResult( intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult( int requestCode, int resultCode, Intent data)
    {
        if ( requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {

            if ( resultCode == RESULT_OK) {

                Toast.makeText(this, " Picture was taken successfully", Toast.LENGTH_SHORT).show();
                /*********** Load Captured Image And Data Start ****************/

                //String imageId = convertImageUriToFile( imageUri,CameraActivity);


                //  Create and excecute AsyncTask to load capture image

                //new LoadImagesFromSDCard().execute(""+imageId);

                /*********** Load Captured Image And Data End ****************/


            } else if ( resultCode == RESULT_CANCELED) {

                Toast.makeText(this, " Picture was not taken ", Toast.LENGTH_SHORT).show();
            } else {

                Toast.makeText(this, " Picture was not taken ", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {

            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                Toast.makeText(context,
                        "Error Creating Image",
                        Toast.LENGTH_SHORT).show();
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(photoFile));
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
        else {
            Toast.makeText(context,
                    "No Camera Activity",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void bindPictureCaptureButton() {
        //btnTakePictureForCheckIn
        ((Button)findViewById(R.id.btnTakePictureForCheckIn)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                capturePic();
                //dispatchTakePictureIntent();
                /*
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
                */
            }
        });
    }

    /*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {

            Integer x = 100;
            Toast.makeText(context,
                    "Image Successfully Created - " + mCurrentPhotoPath,
                    Toast.LENGTH_SHORT).show();

        }

        //GET SMALL IMAGE
        /*
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            imageView.setImageBitmap(imageBitmap);
        }
        else {
            Toast.makeText(context,
                    "Image Not Captured",
                    Toast.LENGTH_SHORT).show();
        }
        */
    //}


    private void setRadioButtonListeners() {

        final RadioGroup rbgPainGroup = ((RadioGroup)findViewById(R.id.rbgPainGroup));
        final RadioButton rbWellControlled = (RadioButton)findViewById(R.id.rbWellControlled);
        final RadioButton rbModerate = (RadioButton)findViewById(R.id.rbModerate);
        final RadioButton rbSevere = (RadioButton)findViewById(R.id.rbSevere);

        rbgPainGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                String info = "";
                if (rbWellControlled.isChecked()) {
                    info = "Well Controlled";
                    Toast.makeText(context,
                            "Well Controlled",
                            Toast.LENGTH_SHORT).show();
                    //lvMedications.setVisibility(View.VISIBLE);
                    //https://code.google.com/p/datetimepicker/source/browse/trunk/src/com/ptashek/widgets/datetimepicker/DateTimePicker.java
                }

                if(rbModerate.isChecked()) {
                    info = "Moderate";
                    Toast.makeText(context,
                            "Moderate",
                            Toast.LENGTH_SHORT).show();
                    //lvMedications.setVisibility(View.INVISIBLE);
                }

                if(rbSevere.isChecked()) {
                    info = "Severe";
                    Toast.makeText(context,
                            "Severe",
                            Toast.LENGTH_SHORT).show();
                    //lvMedications.setVisibility(View.INVISIBLE);
                }
                checkIn.setMouthPain(info);
            }
        });

        final RadioGroup rbgEating = ((RadioGroup)findViewById(R.id.rbgEating));
        final RadioButton rbStopEatingNo = (RadioButton)findViewById(R.id.rbStopEatingNo);
        final RadioButton rbStopEatingSome = (RadioButton)findViewById(R.id.rbStopEatingSome);
        final RadioButton rbStopEatingCannot = (RadioButton)findViewById(R.id.rbStopEatingCannot);

        rbgEating.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                String info = "";
                if (rbStopEatingNo.isChecked()) {
                    info = "No";
                    Toast.makeText(context,
                            "No",
                            Toast.LENGTH_SHORT).show();
                    //lvMedications.setVisibility(View.VISIBLE);
                    //https://code.google.com/p/datetimepicker/source/browse/trunk/src/com/ptashek/widgets/datetimepicker/DateTimePicker.java
                }

                if(rbStopEatingSome.isChecked()) {
                    info = "Some";
                    Toast.makeText(context,
                            "Some",
                            Toast.LENGTH_SHORT).show();
                    //lvMedications.setVisibility(View.INVISIBLE);
                }

                if(rbStopEatingCannot.isChecked()) {
                    info = "Can't Eat";
                    Toast.makeText(context,
                            "Can't Eat",
                            Toast.LENGTH_SHORT).show();
                    //lvMedications.setVisibility(View.INVISIBLE);
                }

                checkIn.setFoodConsumption(info);
            }
        });



        final RadioGroup rbMedicationTakenGroup = ((RadioGroup)findViewById(R.id.rbgCheckInMedicationTaken));
        final RadioButton rbMedicationWasTaken = (RadioButton)findViewById(R.id.rbTookPainMedication);
        final RadioButton rbDidNotTakePainMedication = (RadioButton)findViewById(R.id.rbDidNotTakePainMedication);
        final ListView lvMedications = (ListView)findViewById(R.id.lvPatientCheckInPrescriptionList);

        lvMedications.setVisibility(View.INVISIBLE);


        rbMedicationTakenGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        if (rbMedicationWasTaken.isChecked()) {
                            Toast.makeText(context,
                                    "Medication Taken",
                                    Toast.LENGTH_SHORT).show();
                            lvMedications.setVisibility(View.VISIBLE);
                            //https://code.google.com/p/datetimepicker/source/browse/trunk/src/com/ptashek/widgets/datetimepicker/DateTimePicker.java
                        }

                        if(rbDidNotTakePainMedication.isChecked()) {
                            Toast.makeText(context,
                                    "Medication NOT Taken",
                                    Toast.LENGTH_SHORT).show();
                            lvMedications.setVisibility(View.INVISIBLE);
                        }
                    }
                });
    }

    private void populateViewModel() {
        List<Prescription> prescriptions = repository.GetAllPrescriptionsForPatient(ID);

        for (Prescription p : prescriptions) {
            prescriptionCheckInViewModels.add(new PrescriptionCheckInViewModel(p));
        }
    }

    private void initializeList() {

        //long id = 1234;
        //PhysicianRepository repo = new PhysicianRepository();
        //adapter = new PatientArrayAdapter(this, patients);

        //final List<Patient> patients = //repo.GetAllPatients(id);
        //PatientArrayAdapter adapter = new PatientArrayAdapter(this, patients);



        ListView patientListView = (ListView)findViewById(R.id.lvPatientCheckInPrescriptionList);
        patientListView.setAdapter(adapter);
        patientListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                int index = arg2;
                Toast.makeText(getBaseContext(),
                        "You have selected item : " + prescriptionCheckInViewModels.get(index).getPrescriptionName(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        registerForContextMenu(patientListView);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.patient_checkin, menu);

        //View view = (View) menu.findItem(R.id.save_checkin).getActionView();

        // to get child view - example:
        //ImageView image  = (ImageView)view.findViewById(R.id.my_item);
        //image.setOnClickListener....

        /*
        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //do stuff here
                Toast.makeText(getBaseContext(),
                        "Thank You for Checking In",

                        Toast.LENGTH_SHORT).show();
            }
        });
        */


        return super.onCreateOptionsMenu(menu);
    }



/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.patient_checkin, menu);
        return true;
    }
*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        else if (id == R.id.save_checkin){
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
