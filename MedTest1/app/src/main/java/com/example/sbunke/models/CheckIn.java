package com.example.sbunke.models;

import java.util.Collection;
import java.util.Date;

/**
 * Created by sbunke on 10/17/2014.
 */
public class CheckIn {

    //private

    //http://www.tutorialspoint.com/java/java_date_time.htm
    //get current date
    //Date date = new Date()

    public Date getCheckInDate() {
        return checkInDate;
    }

    private Date checkInDate;

    public CheckIn(Patient patient) {
        this.patient = patient;
        this.checkInDate = new Date();
    }


    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    private Patient patient;

    private String mouthPain;

    private String foodConsumption;

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getMouthPain() {
        return mouthPain;
    }

    public void setMouthPain(String mouthPain) {
        this.mouthPain = mouthPain;
    }

    public String getFoodConsumption() {
        return foodConsumption;
    }

    public void setFoodConsumption(String foodConsumption) {
        this.foodConsumption = foodConsumption;
    }

    public Collection<MedicationTaken> getMedicationTakenCollection() {
        return medicationTakenCollection;
    }

    public void setMedicationTakenCollection(Collection<MedicationTaken> medicationTakenCollection) {
        this.medicationTakenCollection = medicationTakenCollection;
    }

    private Collection<MedicationTaken> medicationTakenCollection;

}
