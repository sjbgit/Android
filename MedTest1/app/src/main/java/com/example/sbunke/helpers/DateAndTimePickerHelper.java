package com.example.sbunke.helpers;

import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by sbunke on 11/14/2014.
 */
public class DateAndTimePickerHelper {

    public static java.util.Date getDateFromDatePickerAndTimePicker(DatePicker datePicker, TimePicker timePicker){
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year =  datePicker.getYear();

        Calendar cal = new GregorianCalendar(year, month, day);

        //GregorianCalendar.getInstance();
        //cal.set(year, month, day);

        Date date = new Date(cal.getTimeInMillis()); // cal.getTime());
        //date.setDate(day);
        //date.setMonth(month);
        //date.setYear(year);

        date.setHours(timePicker.getCurrentHour());
        date.setMinutes(timePicker.getCurrentMinute());

        return date;

        //Calendar calendar = Calendar.getInstance();
        //calendar.set(year, month, day);
        //calendar.set

        //return calendar.getTime();
    }
}
