package com.example.sbunke.helpers;

import android.content.SharedPreferences;
import android.util.Log;

import com.example.sbunke.activities.LoginActivity;
import com.example.sbunke.models.Prescription;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by sbunke on 11/4/2014.
 */
public class SharedPreferencesHelper {
    private final String TAG = "SharedPreferencesHelper";
    //public static SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss a");
    public static SimpleDateFormat formatter = new SimpleDateFormat("EEEE, MMM dd, yyyy HH:mm:ss a");
    public static final String USER_ALARMS = "USER_ALARMS";
    private SharedPreferences sharedPreferences;
    public SharedPreferencesHelper(SharedPreferences sharedPreferences){
        this.sharedPreferences = sharedPreferences;
    }

    public List<Date> getAlarmTimesFromPreferences() {
        Date date = new Date();
        String info = date.toString();


        List<Date> alarmDateTimes = new ArrayList<Date>();
        alarmDateTimes.add(date);

        //saveAlarmTimes(alarmDateTimes);

        String retrievedTimes = sharedPreferences.getString(SharedPreferencesHelper.USER_ALARMS, "");

        String[] splitString = retrievedTimes.split("[\\x7C]");

        List<Date> retrievedAlarms = new ArrayList<Date>();

        for(String item : splitString){
            try {
                Date parsedDate = formatter.parse(item);
                retrievedAlarms.add(parsedDate);

            }
            catch (Exception e){
                Log.e(TAG, "Error in parsing date from user preferences", e);
            }

        }


        return retrievedAlarms;

    }

    public void saveAlarmTimes(List<Date> alarmTimes) {
        if (alarmTimes.size() == 0){
            return;
        }

        String times = "";
        for (Date date : alarmTimes) {
            times += formatter.format(date) + "|";
            //times += date.toString() + "|";
        }

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SharedPreferencesHelper.USER_ALARMS, times);
        editor.commit();




    }
}
