package com.example.sbunke.services;

import com.example.sbunke.models.Physician;

import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by sbunke on 11/20/2014.
 */
public interface PhysicianSvcApi {

    public static final String PHYSICIAN_SVC_PATH = "/physician";

    public static final String ID_PARAMETER = "id";

    //http://localhost:8080/video/search/findByName?title=xxx
    @GET(PHYSICIAN_SVC_PATH + "/{id}")
    public Physician getPhysician(@Path(ID_PARAMETER) long id);



}
