package com.example.sbunke.models;

import java.util.Date;

/**
 * Created by sbunke on 10/20/2014.
 */
public class UserCredentials {
    private String userName;

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    private UserType userType;
    private String password;

    //private String medicalRecordNumber;
    public UserCredentials(String userName, String password, UserType userType) {
        super();
        this.userName = userName;
        this.password = password;
        this.userType = userType;
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
