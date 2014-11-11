package com.example.sbunke.testretro1;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by sbunke on 11/10/2014.
 */
public interface RestApi {

    @POST("/user/")
    void addUser(@Body User user, Callback<Response> responseCallback);

    @GET("/user/{id}")
    User getUser(@Path("id") int id);

}
