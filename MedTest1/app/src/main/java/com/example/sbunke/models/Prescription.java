package com.example.sbunke.models;

/**
 * Created by sbunke on 10/17/2014.
 */
public class Prescription {

    public Prescription(String name){
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    private String dosage;
}
