package com.example.sbunke.helpers;

import com.example.sbunke.models.Patient;
import com.example.sbunke.repositories.CheckInRepository;
import com.example.sbunke.repositories.PhysicianRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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


    public Collection<String> GetAllAlertsForAllPatients() {
        CheckInRepository repository = new CheckInRepository();

        List<String> alerts = new ArrayList<String>();

        for (Patient p : patients) {
            List<String> patientAlerts = repository.GetThresholdAlertsForPatientCheckIns(p.getId());
            alerts.addAll(patientAlerts);
        }

        return alerts;
    }


}
