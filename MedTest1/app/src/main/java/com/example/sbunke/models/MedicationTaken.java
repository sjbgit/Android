package com.example.sbunke.models;

/**
 * Created by sbunke on 10/30/2014.
 */
public class MedicationTaken {

    public boolean medicationWasTaken() {
        return medicationWasTaken;
    }

    public void setMedicationWasTaken(boolean medicationWasTaken) {
        this.medicationWasTaken = medicationWasTaken;
    }

    public String getMedicationName() {
        return medicationName;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    private boolean medicationWasTaken;

    private String medicationName;
}
