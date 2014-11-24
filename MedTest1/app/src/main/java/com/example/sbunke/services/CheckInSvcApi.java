package com.example.sbunke.services;

/**
 * Created by sbunke on 11/21/2014.
 */
import com.example.sbunke.models.CheckIn;
import com.example.sbunke.models.CheckInMini;
import com.example.sbunke.viewmodels.PrescriptionCheckInViewModel;

import retrofit.http.Body;
import retrofit.http.POST;

public interface CheckInSvcApi {

    public static final String CHECKIN_SVC_PATH = "/checkin";

    @POST(CHECKIN_SVC_PATH)
    public boolean addCheckIn(@Body CheckInMini checkIn);

}