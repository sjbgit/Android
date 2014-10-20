package com.example.sbunke.models;

import java.util.Date;

/**
 * Created by sbunke on 10/20/2014.
 */
public class UserCredentials {
    private String userName;

    private String password;

    //private String medicalRecordNumber;
    public UserCredentials(String userName, String password) {
        super();
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
