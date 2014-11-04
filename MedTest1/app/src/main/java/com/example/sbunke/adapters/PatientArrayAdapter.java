package com.example.sbunke.adapters;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sbunke.activities.R;
import com.example.sbunke.filters.PatientFilter;
import com.example.sbunke.models.Patient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sbunke on 10/16/2014.
 */
public class PatientArrayAdapter extends ArrayAdapter<Patient> implements Filterable{

    private final Activity context;
    private List<Patient> patients; // = new ArrayList<Patient>();
    private List<Patient> filteredPatients;
    //protected final Integer[] imageIds;
    private PatientFilter patientFilter;


    public PatientArrayAdapter(
            Activity context, List<Patient> patients) {
        super(context, R.layout.patient_row_layout, patients);
        this.context = context;
        this.patients = patients;
        this.filteredPatients = patients;
        //this.imageIds = imageIds;
    }

    public int getCount() {
        return filteredPatients.size();
    }

    public Patient getItem(int position) {
        return filteredPatients.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    static class ViewContainer {
        public ImageView imageView;
        public TextView firstNameTv;
        public TextView lastNameTv;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewContainer viewContainer;
        View rowView = view;

        //---print the index of the row to examine---
        Log.d("PatientArrayAdapter", String.valueOf(position));

        //---if the row is displayed for the first time---
        if (rowView == null) {

            Log.d("PatientArrayAdapter", "New");
            LayoutInflater inflater = context.getLayoutInflater();
            rowView = inflater.inflate(R.layout.patient_row_layout, null, true);

            //---create a view container object---
            viewContainer = new ViewContainer();

            //---get the references to all the views in the row---
            viewContainer.firstNameTv = (TextView)
                    rowView.findViewById(R.id.patientFirstNameTv);
            viewContainer.lastNameTv = (TextView)
                    rowView.findViewById(R.id.patientLastNameTv);
            viewContainer.imageView = (ImageView)
                    rowView.findViewById(R.id.icon);

            //---assign the view container to the rowView---
            rowView.setTag(viewContainer);
        } else {

            //---view was previously created; can recycle---
            Log.d("CustomArrayAdapter", "Recycling");
            //---retrieve the previously assigned tag to get
            // a reference to all the views; bypass the findViewByID() process,
            // which is computationally expensive---
            viewContainer = (ViewContainer) rowView.getTag();
        }

        //---customize the content of each row based on position---
        viewContainer.firstNameTv.setText(patients.get(position).getFirstName()); //[position]);
        viewContainer.lastNameTv.setText(patients.get(position).getLastName() + " ...Some descriptions here...");
        //viewContainer.imageView.setImageResource(imageIds[position]);
        return rowView;
    }

    @Override
    public Filter getFilter() {
        if (patientFilter == null) {
            patientFilter = new PatientFilter();
        }


        return patientFilter;
    }

    public class PatientFilter extends Filter {

        //private List<Patient> patients;
/*
        public PatientFilter(List<Patient> patients) {
            this.patients = patients;
        }
  */


        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

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


            //this works
            /*
            results.values = patients;
            results.count = patients.size();
            */
            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint,FilterResults results) {
            // Now we have to inform the adapter about the new list filtered
            if (results.count == 0)
                notifyDataSetInvalidated();
            else {
                filteredPatients = (List<Patient>) results.values;
                notifyDataSetChanged();
            }

        }

    }

}
