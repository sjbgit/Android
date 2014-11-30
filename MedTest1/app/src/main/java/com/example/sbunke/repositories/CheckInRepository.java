package com.example.sbunke.repositories;

import com.example.sbunke.models.CheckIn;
import com.example.sbunke.models.PatientCheckIn;
import com.example.sbunke.services.ServiceHelper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

/**
 * Created by sbunke on 11/28/2014.
 */
public class CheckInRepository {

    public void GetAllCheckInsForPatientWithinHoursRange(String patientId) {
        Collection<PatientCheckIn> checkIns = ServiceHelper.GetAllCheckInsByPatientIdSync(patientId);








/*
        Collection<PatientCheckIn> checkIns = new ArrayList<PatientCheckIn>();

        Collection<PatientCheckIn> relevantCheckIns16Hours = new ArrayList<PatientCheckIn>();
        Collection<PatientCheckIn> relevantCheckIns12Hours = new ArrayList<PatientCheckIn>();

        Calendar minus16Hours = Calendar.getInstance();
        minus16Hours.add(Calendar.HOUR, -16);

        Calendar minus12Hours = Calendar.getInstance();
        minus16Hours.add(Calendar.HOUR, -12);

        for (PatientCheckIn pci : checkIns) {
            Calendar pciDate = Calendar.getInstance();
            pciDate.setTimeInMillis(pci.getCheckInDate());
            if (pciDate.after(minus16Hours)) {
                relevantCheckIns16Hours.add(pci);
            }
            if (pciDate.after(minus12Hours)) {
                relevantCheckIns12Hours.add(pci);
            }
        }
*/







    }


}
