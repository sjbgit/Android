package com.example.sbunke.helpers;

import com.example.sbunke.models.Patient;
import com.example.sbunke.repositories.PhysicianRepository;

import java.util.Collection;

/**
 * Created by sbunke on 11/28/2014.
 */
public class PhysicianNotificationHelper {

    private PhysicianRepository physicianRepository;
    private Collection<Patient> patients;


    public PhysicianNotificationHelper(String physicianId) {
        physicianRepository = new PhysicianRepository();
        patients = physicianRepository.GetAllPatients(physicianId);

    }


    public void DeterminePatientsExceedingThresholds() {




    }


}
