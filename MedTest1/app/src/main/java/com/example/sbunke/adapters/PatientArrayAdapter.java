package com.example.sbunke.adapters;

import android.app.Activity;
import android.widget.ArrayAdapter;

import com.example.sbunke.medtest1.R;
import com.example.sbunke.models.Patient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sbunke on 10/16/2014.
 */
public class PatientArrayAdapter extends ArrayAdapter<Patient> {

    private final Activity context;
    private List<Patient> patients; // = new ArrayList<Patient>();
    //protected final Integer[] imageIds;

    public PatientArrayAdapter(
            Activity context, List<Patient> patients) {
        super(context, R.layout.lvrowlayout2, patients);
        this.context = context;
        this.patients = patients;
        //this.imageIds = imageIds;
    }
}
