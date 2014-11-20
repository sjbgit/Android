package com.example.sbunke.testretro1;

import android.provider.MediaStore;

import java.util.Collection;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by sbunke on 11/19/2014.
 */
public interface UserSvcApi {

    public static final String TITLE_PARAMETER = "title";

    public static final String DURATION_PARAMETER = "duration";

    // The path where we expect the VideoSvc to live
    public static final String USER_SVC_PATH = "/user";

    // The path to search videos by title
    public static final String VIDEO_TITLE_SEARCH_PATH = USER_SVC_PATH + "/search/findByName";

    // The path to search videos by title
    public static final String VIDEO_DURATION_SEARCH_PATH = USER_SVC_PATH + "/search/findByDurationLessThan";

    @GET(USER_SVC_PATH)
    public Collection<User> getUserList();

    @POST(USER_SVC_PATH)
    public Void addUser(@Body User v);

    //http://localhost:8080/video/search/findByName?title=xxx
    @GET(VIDEO_TITLE_SEARCH_PATH)
    public Collection<MediaStore.Video> findByTitle(@Query(TITLE_PARAMETER) String title);

    @GET(VIDEO_DURATION_SEARCH_PATH)
    public Collection<MediaStore.Video> findByDurationLessThan(@Query(DURATION_PARAMETER) String title);

}
