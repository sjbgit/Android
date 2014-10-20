package com.example.sbunke.models;

/**
 * Created by sbunke on 10/20/2014.
 */

/*
public enum UserType {
    Patient,
    Physician,
    Invalid
}
*/

public final class UserType {
    public static final String INVALID = "INVALID";
    public static final String PATIENT = "PATIENT";
    public static final String PHYSICIAN = "PHYSICIAN";
    /*
    Invalid(0),
    Patient(1),
    Physician(2);

    private final int value;

    private UserType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    */
}
