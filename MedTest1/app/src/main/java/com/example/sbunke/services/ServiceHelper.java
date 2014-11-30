package com.example.sbunke.services;

import android.os.AsyncTask;

import com.example.sbunke.models.CheckIn;
import com.example.sbunke.models.Patient;
import com.example.sbunke.models.PatientCheckIn;
import com.example.sbunke.models.Physician;
import com.example.sbunke.models.Prescription;
import com.example.sbunke.models.PrescriptionCheckIn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import retrofit.RestAdapter;

/**
 * Created by sbunke on 11/20/2014.
 */
public class ServiceHelper {

    private final static String ADDRESS = "http://192.168.0.5:8080";  //http://10.41.4.140:8080";

    public static Patient UpdatePatientPrescriptionsSync(String patientId, List<Prescription> prescriptions) {
        final PatientSvcApi restApi = new RestAdapter.Builder().setEndpoint(ADDRESS).build().create(PatientSvcApi.class);
        return restApi.updatePatientPrescriptions(patientId, prescriptions);
    }

    public static Credentials GetCredentialsSync(String username, String password) {
        final CredentialsSvcApi restApi = new RestAdapter.Builder().setEndpoint(ADDRESS).build().create(CredentialsSvcApi.class);
        return restApi.getCredentials(username,password);
    }

    public static Collection<Patient> GetPatientsByPhysicianIdSync(String physicianId) {
        final PatientSvcApi restApi = new RestAdapter.Builder().setEndpoint(ADDRESS).build().create(PatientSvcApi.class);
        return restApi.getPatientsByPhysicianId(physicianId);
    }

    public static void UpdatePatientPrescriptionsAsync(String patientId, List<Prescription> prescriptions) {
        final String innerId = patientId;
        final List<Prescription> innerPrescriptions = prescriptions;
        final PatientSvcApi restApi = new RestAdapter.Builder().setEndpoint(ADDRESS).build().create(PatientSvcApi.class);

        new AsyncTask<Void, Void, Patient>() {

            @Override
            protected Patient doInBackground(Void... voids) {
                Patient patient = null;
                try {

                    patient = restApi.updatePatientPrescriptions(innerId, innerPrescriptions);
                    String x = "test";

                }
                catch (Exception ex) {
                    Exception e = ex;
                }

                return patient;

            }
        }.execute();

    }

    public static void GetPatientsByPhysicianId(String physicianId) {
        final String innerId = physicianId;
        final PatientSvcApi restApi = new RestAdapter.Builder().setEndpoint(ADDRESS).build().create(PatientSvcApi.class);
        //Collection<Patient> patients;

        new AsyncTask<Void, Void, Collection<Patient>>() {

            @Override
            protected Collection<Patient> doInBackground(Void... voids) {
                Collection<Patient> patients = new ArrayList<Patient>();
                try {

                    //PatientCheckIn pci = new PatientCheckIn();
                    //pci.setMouthPain("test mouth pain");
                    //boolean result = restApi.addCheckIn(innerCheckIn);

                    patients = restApi.getPatientsByPhysicianId(innerId);
                    String x = "test";

                }
                catch (Exception ex) {
                    Exception e = ex;
                }



                return patients;

                //return result;  //restApi.getUserList();
            }
        }.execute();
    }

    public static void AddCheckIn(PatientCheckIn patientCheckIn) {

        final PatientCheckIn innerCheckIn = patientCheckIn;
        final CheckInSvcApi restApi = new RestAdapter.Builder().setEndpoint(ADDRESS).build().create(CheckInSvcApi.class);

        new AsyncTask<Void, Void, PatientCheckIn>() {

            @Override
            protected PatientCheckIn doInBackground(Void... voids) {
                try {

                    //PatientCheckIn pci = new PatientCheckIn();
                    //pci.setMouthPain("test mouth pain");
                    boolean result = restApi.addCheckIn(innerCheckIn);
                }
                catch (Exception ex) {
                    Exception e = ex;
                }



                return innerCheckIn;

                //return result;  //restApi.getUserList();
            }
        }.execute();

    }

    public static Collection<PatientCheckIn> GetAllCheckInsByPatientIdSync(final String patientId) {
        final CheckInSvcApi restApi = new RestAdapter.Builder().setEndpoint(ADDRESS).build().create(CheckInSvcApi.class);
        return restApi.getCheckInsByPatientId(patientId);

    }

    public static void GetAllCheckInsByPatientId(final String patientId) {

        //final PatientCheckIn innerCheckIn = patientCheckIn;
        final CheckInSvcApi restApi = new RestAdapter.Builder().setEndpoint(ADDRESS).build().create(CheckInSvcApi.class);



        new AsyncTask<Void, Void, Collection<PatientCheckIn>>() {

            @Override
            protected Collection<PatientCheckIn> doInBackground(Void... voids) {

                Collection<PatientCheckIn> checkIns = new ArrayList<PatientCheckIn>();

                try {

                    //PatientCheckIn pci = new PatientCheckIn();
                    //pci.setMouthPain("test mouth pain");
                    checkIns = restApi.getCheckInsByPatientId(patientId);
                    //return checkIns;
                }
                catch (Exception ex) {
                    Exception e = ex;
                }



                return checkIns; //new ArrayList<PatientCheckIn>();

                //return result;  //restApi.getUserList();
            }
        }.execute();

    }


    public void GetCredentials() {

        final CredentialsSvcApi restApi = new RestAdapter.Builder().setEndpoint(ADDRESS).build().create(CredentialsSvcApi.class);

        new AsyncTask<Void, Void, Credentials>() {

            @Override
            protected Credentials doInBackground(Void... voids) {
                Credentials creds = restApi.getCredentials("username", "password");

                return creds;  //restApi.getUserList();
            }
        }.execute();

    }

    public void GetPhysician(long id) {

        final long innerId = id;
        final PhysicianSvcApi restApi = new RestAdapter.Builder().setEndpoint(ADDRESS).build().create(PhysicianSvcApi.class);

        new AsyncTask<Void, Void, Physician>() {

            @Override
            protected Physician doInBackground(Void... voids) {
                Physician physician = restApi.getPhysician(innerId);

                return physician;  //restApi.getUserList();
            }
        }.execute();

    }

}
