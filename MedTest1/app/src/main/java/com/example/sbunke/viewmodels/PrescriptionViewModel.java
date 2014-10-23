package com.example.sbunke.viewmodels;

import java.util.Date;

/**
 * Created by sbunke on 10/22/2014.
 */
public class PrescriptionViewModel {

    public boolean isMedicationWasTaken() {
        return medicationWasTaken;
    }

    public void setMedicationWasTaken(boolean medicationWasTaken) {
        this.medicationWasTaken = medicationWasTaken;
    }

    private boolean medicationWasTaken;

    public Date getDateTaken() {
        return dateTaken;
    }

    public void setDateTaken(Date dateTaken) {
        this.dateTaken = dateTaken;
    }

    private Date dateTaken;

    public long getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(long prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    private long prescriptionId;

    private String prescriptionName;

    public String getPrescriptionName() {
        return prescriptionName;
    }

    public void setPrescriptionName(String prescriptionName) {
        this.prescriptionName = prescriptionName;
    }
}
