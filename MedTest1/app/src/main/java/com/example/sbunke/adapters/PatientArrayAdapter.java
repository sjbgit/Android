package com.example.sbunke.adapters;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sbunke.activities.R;
import com.example.sbunke.models.Patient;

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
        super(context, R.layout.patient_row_layout, patients);
        this.context = context;
        this.patients = patients;
        //this.imageIds = imageIds;
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
}
