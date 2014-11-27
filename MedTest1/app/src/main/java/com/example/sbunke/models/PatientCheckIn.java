package com.example.sbunke.models;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by sbunke on 11/21/2014.
 */
public class PatientCheckIn {



    //private

    //http://www.tutorialspoint.com/java/java_date_time.htm
    //get current date
    //Date date = new Date()

    public long getCheckInDate() {

        return checkInDate;
    }

    private long checkInDate;

    public PatientCheckIn() {
        this.checkInDate = System.currentTimeMillis();
        this.prescriptionCheckIns = new ArrayList<PrescriptionCheckIn>();
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getPhysicianId() {
        return physicianId;
    }

    public void setPhysicianId(String physicianId) {
        this.physicianId = physicianId;
    }

    private String patientId;

    private String physicianId;

    private String mouthPain;

    public String getMouthPain() {
        return mouthPain;
    }

    public void setMouthPain(String mouthPain) {
        this.mouthPain = mouthPain;
    }

    private String foodConsumption;

    public String getFoodConsumption() {
        return foodConsumption;
    }

    public void setFoodConsumption(String foodConsumption) {
        this.foodConsumption = foodConsumption;
    }


    public Collection<PrescriptionCheckIn> getPrescriptionCheckIns() {
        return prescriptionCheckIns;
    }

    public void setPrescriptionCheckIns(Collection<PrescriptionCheckIn> prescriptionCheckIns) {
        this.prescriptionCheckIns = prescriptionCheckIns;
    }

    private Collection<PrescriptionCheckIn> prescriptionCheckIns;


    /*

    public PrescriptionCheckIn[] getPrescriptionCheckIns() {
        return prescriptionCheckIns;
    }

    public void setPrescriptionCheckIns(PrescriptionCheckIn[] prescriptionCheckIns) {
        this.prescriptionCheckIns = prescriptionCheckIns;
    }

    private PrescriptionCheckIn[] prescriptionCheckIns;

*/

}