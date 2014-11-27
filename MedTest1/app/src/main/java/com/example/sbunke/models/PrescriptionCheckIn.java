package com.example.sbunke.models;

/**
 * Created by sbunke on 11/24/2014.
 */
public class PrescriptionCheckIn {

    public PrescriptionCheckIn() {

    }

    private long prescriptionCheckInDate;

    public long getPrescriptionCheckInDate() {
        return prescriptionCheckInDate;
    }

    public void setPrescriptionCheckInDate(long prescriptionCheckInDate) {
        this.prescriptionCheckInDate = prescriptionCheckInDate;
    }

    private String prescriptionName;

    public String getPrescriptionName() {
        return prescriptionName;
    }

    public void setPrescriptionName(String prescriptionName) {
        this.prescriptionName = prescriptionName;
    }

    private String prescriptionDosage;

    public String getPrescriptionDosage() {
        return prescriptionDosage;
    }

    public void setPrescriptionDosage(String prescriptionDosage) {
        this.prescriptionDosage = prescriptionDosage;
    }

}
