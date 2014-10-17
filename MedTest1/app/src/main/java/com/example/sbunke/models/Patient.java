package com.example.sbunke.models;

import java.util.Collection;

/**
 * Created by sbunke on 10/16/2014.
 */
public class Patient extends User {

    public Patient(String firstName) {
        this.setFirstName(firstName);
    }

    private Collection<Prescription> prescriptions;

    public Collection<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(Collection<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }
}
