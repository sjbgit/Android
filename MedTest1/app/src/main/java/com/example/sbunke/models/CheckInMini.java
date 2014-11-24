package com.example.sbunke.models;

import java.util.Date;

/**
 * Created by sbunke on 11/21/2014.
 */
public class CheckInMini {



    //private

    //http://www.tutorialspoint.com/java/java_date_time.htm
    //get current date
    //Date date = new Date()

    public Date getCheckInDate() {

        return checkInDate;
    }

    private Date checkInDate;

    public CheckInMini() {
        //this.checkInDate = new Date();
    }

    //public CheckInMini(Patient patient) {
    //    this.patient = patient;
    //    this.checkInDate = new Date();
    //}

    //public Patient getPatient() {
    //    return patient;
    //}

}