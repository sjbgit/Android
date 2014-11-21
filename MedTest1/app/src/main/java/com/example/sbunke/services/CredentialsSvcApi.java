package com.example.sbunke.services;

import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by sbunke on 11/20/2014.
 */
public interface CredentialsSvcApi {

    public static final String CREDENTIALS_SVC_PATH = "/credentials";

    public static final String USERNAME_PARAMETER = "username";
    public static final String PASSWORD_PARAMETER = "password";

    //http://localhost:8080/video/search/findByName?title=xxx
    @GET(CREDENTIALS_SVC_PATH + "/{username}/{password}")
    public Credentials getCredentials(@Path(USERNAME_PARAMETER) String userName, @Path(PASSWORD_PARAMETER) String password);

}
