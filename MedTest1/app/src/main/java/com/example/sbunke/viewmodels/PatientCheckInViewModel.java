package com.example.sbunke.viewmodels;

import java.util.Date;

/**
 * Created by sbunke on 11/13/2014.
 */
public class PatientCheckInViewModel {

    public PatientCheckInViewModel(Date checkInDate, String mainInformation, String secondaryInformation){
        this.checkInDate = checkInDate;
        this.mainInformation = mainInformation;
        this.secondaryInformation = secondaryInformation;
    }

    public Date checkInDate;
    public String mainInformation;
    public String secondaryInformation;

}
