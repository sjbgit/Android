package com.example.sbunke.services;

import android.os.AsyncTask;

import com.example.sbunke.models.Physician;
import com.example.sbunke.models.User;

import java.util.Collection;

import retrofit.RestAdapter;

/**
 * Created by sbunke on 11/20/2014.
 */
public class ServiceHelper {

    private final static String address = "http://10.41.4.140:8080";

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
