package com.example.sbunke.filters;

import android.widget.Filter;

import com.example.sbunke.models.Patient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sbunke on 11/3/2014.
 */
public class PatientFilter extends Filter {

    private List<Patient> patients;

    public PatientFilter(List<Patient> patients) {
        this.patients = patients;
    }
    @Override
    protected Filter.FilterResults performFiltering(CharSequence constraint) {
        FilterResults results = new FilterResults();
        // We implement here the filter logic
        if (constraint == null || constraint.length() == 0) {
            // No filter implemented we return all the list
            results.values = patients;
            results.count = patients.size();
        }
        else {
            // We perform filtering operation
            List<Patient> nPatientList = new ArrayList<Patient>();

            for (Patient p : patients) {
                if (p.getLastName().toUpperCase().startsWith(constraint.toString().toUpperCase()))
                    nPatientList.add(p);
            }

            results.values = nPatientList;
            results.count = nPatientList.size();

        }
        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint,FilterResults results) {
        // Now we have to inform the adapter about the new list filtered
        /*
        if (results.count == 0)
            //notifyDataSetInvalidated();
        else {
            patients = (List<Patient>) results.values;
            notifyDataSetChanged();
        }
        */

    }

}
