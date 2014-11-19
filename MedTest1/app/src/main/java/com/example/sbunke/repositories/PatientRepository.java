package com.example.sbunke.repositories;

import com.example.sbunke.models.Patient;
import com.example.sbunke.models.Prescription;
import com.example.sbunke.viewmodels.PatientCheckInViewModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by sbunke on 10/23/2014.
 */
public class PatientRepository {

    public void SaveCheckIn(PatientCheckInViewModel checkInViewModel) {

    }

    public List<Prescription> GetAllPrescriptionsForPatient(long id) {
        List<Prescription> prescriptions = new ArrayList<Prescription>();
        prescriptions.add(new Prescription("Lortab", "1000 mg"));
        prescriptions.add(new Prescription("OxyContin", "250 mg"));

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

    public List<PatientCheckInViewModel> getAllCheckInsForPatient(long id) {
        List<PatientCheckInViewModel> checkIns = new ArrayList<PatientCheckInViewModel>();
        checkIns.add(new PatientCheckInViewModel(new Date(), "main 1", "secondary 1"));
        checkIns.add(new PatientCheckInViewModel(new Date(), "main 2", "secondary 2"));
        checkIns.add(new PatientCheckInViewModel(new Date(), "main 3", "secondary 3"));
        checkIns.add(new PatientCheckInViewModel(new Date(), "main 4", "secondary 4"));
        checkIns.add(new PatientCheckInViewModel(new Date(), "main 5", "secondary 5"));
        checkIns.add(new PatientCheckInViewModel(new Date(), "main 6", "secondary 6"));
        checkIns.add(new PatientCheckInViewModel(new Date(), "main 7", "secondary 7"));
        checkIns.add(new PatientCheckInViewModel(new Date(), "main 8", "secondary 8"));


        return checkIns;
    }
}
