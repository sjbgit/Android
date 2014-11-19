package com.example.sbunke.repositories;

import com.example.sbunke.models.Patient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sbunke on 10/16/2014.
 */
public class PhysicianRepository {


    public List<Patient> GetAllPatients(Long physicianId) {
        List<Patient> patients = new ArrayList<Patient>();
        patients.add(new Patient("first name1", "alastname1"));
        patients.add(new Patient("first name2", "blastname2"));
        patients.add(new Patient("first name3", "clastname3"));
        patients.add(new Patient("first name4", "cclastname4"));
        patients.add(new Patient("first name5", "elastname5"));
        patients.add(new Patient("first name6", "flastname6"));
        patients.add(new Patient("first name7", "glastname7"));
        patients.add(new Patient("first name8", "gglastname18"));

        return patients;
    }


    public void GetAllCheckInsExceedingNotificationThresholds() {
        //get all check-ins for the last 24 hours - pass in physician id - get back

    }



}
