package com.example.sbunke.services;

/**
 * Created by sbunke on 11/21/2014.
 */
import com.example.sbunke.models.PatientCheckIn;

import java.util.Collection;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

public interface CheckInSvcApi {

    public static final String CHECKIN_SVC_PATH = "/checkin";

    @POST(CHECKIN_SVC_PATH)
    public boolean addCheckIn(@Body PatientCheckIn checkIn);

    @GET(CHECKIN_SVC_PATH + "/{id}")
    public Collection<PatientCheckIn> getCheckInsByPatientId(@Path("id") String id);

}