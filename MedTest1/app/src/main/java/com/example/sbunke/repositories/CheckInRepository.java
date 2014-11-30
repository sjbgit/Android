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
import java.util.List;

/**
 * Created by sbunke on 11/28/2014.
 */
public class CheckInRepository {

    public List<String> GetThresholdAlertsForPatientCheckIns(String patientId) {

        List<String> alerts = new ArrayList<String>();

        //received ordered by check in date descending
        Collection<PatientCheckIn> checkIns = ServiceHelper.GetAllCheckInsByPatientIdSync(patientId);

        //nothing to do
        if (checkIns.size() == 0) {
            return alerts;
        }

        PatientCheckIn[] items = checkIns.toArray(new PatientCheckIn[checkIns.size()]);

        //if latest is severe
        if (items[0].getMouthPain().toUpperCase().equals("SEVERE")) {

            //get time of last check in then subtract 16 hours
            Calendar minus12Hours = Calendar.getInstance();
            minus12Hours.setTimeInMillis(items[0].getCheckInDate());
            minus12Hours.add(Calendar.HOUR, -12);

            PatientCheckIn last = null;
            for (PatientCheckIn pci : checkIns) {
                //if last check
                last = pci;
                if (!pci.getMouthPain().toUpperCase().equals("SEVERE")) {
                    continue;
                }

            }

            //earliest consecutive check in that is severe
            Calendar lastCal = Calendar.getInstance();
            lastCal.setTimeInMillis(last.getCheckInDate());
            if (lastCal.before(minus12Hours)) {
                String message = "Patient: " + patientId + " has exceeded 12 hours of SEVERE PAIN";
                alerts.add(message);
            }

        }


        if (items[0].getMouthPain().toUpperCase().equals("SEVERE") || items[0].getMouthPain().toUpperCase().equals("MODERATE")) {

            Calendar minus16Hours = Calendar.getInstance();
            minus16Hours.setTimeInMillis(items[0].getCheckInDate());
            minus16Hours.add(Calendar.HOUR, -16);

            PatientCheckIn lastSevereOrModerate = null;
            for (PatientCheckIn pci : checkIns) {
                //if last check
                lastSevereOrModerate = pci;
                if (!pci.getMouthPain().toUpperCase().equals("SEVERE") && !pci.getMouthPain().toUpperCase().equals("MODERATE")) {
                    continue;
                }

                //earliest consecutive check in that is severe
                Calendar lastCal = Calendar.getInstance();
                lastCal.setTimeInMillis(lastSevereOrModerate.getCheckInDate());
                if (lastCal.before(minus16Hours)) {
                    String message = "Patient: " + patientId + " has exceeded 16 hours of SEVERE OR MODERATE PAIN";
                    alerts.add(message);
                }

            }

        }
        if (items[0].getFoodConsumption().toUpperCase().equals("I CAN'T EAT")) {

            //get time of last check in then subtract 16 hours
            Calendar minus12Hours = Calendar.getInstance();
            minus12Hours.setTimeInMillis(items[0].getCheckInDate());
            minus12Hours.add(Calendar.HOUR, -12);

            PatientCheckIn last = null;
            for (PatientCheckIn pci : checkIns) {
                //if last check
                last = pci;
                if (!pci.getMouthPain().toUpperCase().equals("I CAN'T EAT")) {
                    continue;
                }

            }

            //earliest consecutive check in that is severe
            Calendar lastCal = Calendar.getInstance();
            lastCal.setTimeInMillis(last.getCheckInDate());
            if (lastCal.before(minus12Hours)) {
                String message = "Patient: " + patientId + " has exceeded 12 hours of I CAN'T EAT";
                alerts.add(message);
            }

        }


        return alerts;

    }
}


