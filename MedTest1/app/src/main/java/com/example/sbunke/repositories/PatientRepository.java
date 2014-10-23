package com.example.sbunke.repositories;

import com.example.sbunke.models.Patient;
import com.example.sbunke.models.Prescription;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sbunke on 10/23/2014.
 */
public class PatientRepository {

    public List<Prescription> GetAllPrescriptionsForPatient(long id) {
        List<Prescription> prescriptions = new ArrayList<Prescription>();
        prescriptions.add(new Prescription("Lortab"));
        prescriptions.add(new Prescription("OxyContin"));

        /*
        prescriptions.add(new Prescription("first name3"));
        prescriptions.add(new Prescription("first name4"));
        prescriptions.add(new Prescription("first name5"));
        prescriptions.add(new Prescription("first name6"));
        prescriptions.add(new Prescription("first name7"));
        prescriptions.add(new Prescription("first name8"));
*/
        return prescriptions;

    }
}
