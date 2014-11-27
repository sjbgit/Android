package com.example.sbunke.models;

import java.util.Collection;

/**
 * Created by sbunke on 10/16/2014.
 */
public class Patient extends User {

    public Patient(String firstName, String lastName) {
        this.setLastName(lastName);
        this.setFirstName(firstName);
    }

    public Physician getPhysician() {
        return physician;
    }

    public void setPhysician(Physician physician) {
        this.physician = physician;
    }

    private Physician physician;

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

    private String physicianId;

    public String getPhysicianId() {
        return physicianId;
    }

    public void setPhysicianId(String physicianId) {
        this.physicianId = physicianId;
    }
}
