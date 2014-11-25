package com.example.sbunke.services;

import android.os.AsyncTask;

import com.example.sbunke.models.CheckIn;
import com.example.sbunke.models.PatientCheckIn;
import com.example.sbunke.models.Physician;

import retrofit.RestAdapter;

/**
 * Created by sbunke on 11/20/2014.
 */
public class ServiceHelper {

    private final static String address = "http://10.41.4.140:8080";

    public static Credentials GetCredentialsSync(String username, String password) {
        final CredentialsSvcApi restApi = new RestAdapter.Builder().setEndpoint("http://10.41.4.140:8080").build().create(CredentialsSvcApi.class);
        return restApi.getCredentials(username,password);
    }

    public static void AddCheckIn(PatientCheckIn patientCheckIn) {

        final PatientCheckIn innerCheckIn = patientCheckIn;
        final CheckInSvcApi restApi = new RestAdapter.Builder().setEndpoint("http://10.41.4.140:8080").build().create(CheckInSvcApi.class);

        new AsyncTask<Void, Void, PatientCheckIn>() {

            @Override
            protected PatientCheckIn doInBackground(Void... voids) {
                try {

                    PatientCheckIn pci = new PatientCheckIn();
                    pci.setMouthPain("test mouth pain");
                    boolean result = restApi.addCheckIn(pci);
                }
                catch (Exception ex) {
                    Exception e = ex;
                }



                return innerCheckIn;

                //return result;  //restApi.getUserList();
            }
        }.execute();

    }


    public void GetCredentials() {

        final CredentialsSvcApi restApi = new RestAdapter.Builder().setEndpoint("http://10.41.4.140:8080").build().create(CredentialsSvcApi.class);

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
        final PhysicianSvcApi restApi = new RestAdapter.Builder().setEndpoint("http://10.41.4.140:8080").build().create(PhysicianSvcApi.class);

        new AsyncTask<Void, Void, Physician>() {

            @Override
            protected Physician doInBackground(Void... voids) {
                Physician physician = restApi.getPhysician(innerId);

                return physician;  //restApi.getUserList();
            }
        }.execute();

    }

}
