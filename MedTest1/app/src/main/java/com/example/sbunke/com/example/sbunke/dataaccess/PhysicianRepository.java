package com.example.sbunke.com.example.sbunke.dataaccess;

import com.example.sbunke.models.Patient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sbunke on 10/16/2014.
 */
public class PhysicianRepository {


    public List<Patient> GetAllPatients(Long physicianId) {
        List<Patient> patients = new ArrayList<Patient>();
        patients.add(new Patient("first name1"));
        patients.add(new Patient("first name2"));
        patients.add(new Patient("first name3"));
        patients.add(new Patient("first name4"));
        patients.add(new Patient("first name5"));
        patients.add(new Patient("first name6"));
        patients.add(new Patient("first name7"));
        patients.add(new Patient("first name8"));
                     
        return patients;
    }


}
