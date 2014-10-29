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
import com.example.sbunke.models.Prescription;

import java.util.List;

/**
 * Created by sbunke on 10/28/2014.
 */
public class PatientPrescriptionsArrayAdapter extends ArrayAdapter<Prescription> {



        private final Activity context;
        private List<Prescription> prescriptions; // = new ArrayList<Patient>();
        //protected final Integer[] imageIds;

        public PatientPrescriptionsArrayAdapter(
                Activity context, List<Prescription> prescriptions) {
            super(context, R.layout.patient_prescription_row_layout, prescriptions);
            this.context = context;
            this.prescriptions = prescriptions;
            //this.imageIds = imageIds;
        }

        static class ViewContainer {
            //public ImageView imageView;
            public TextView tvName;
            public TextView tvDosage;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            ViewContainer viewContainer;
            View rowView = view;

            //---print the index of the row to examine---
            Log.d("PatientPrescriptionsArrayAdapter", String.valueOf(position));

            //---if the row is displayed for the first time---
            if (rowView == null) {

                Log.d("PatientPrescriptionsArrayAdapter", "New");
                LayoutInflater inflater = context.getLayoutInflater();
                rowView = inflater.inflate(R.layout.patient_prescription_row_layout, null, true);

                //---create a view container object---
                viewContainer = new ViewContainer();

                //---get the references to all the views in the row---
                viewContainer.tvName = (TextView)
                        rowView.findViewById(R.id.tvMedicationRowName);
                viewContainer.tvDosage = (TextView)
                        rowView.findViewById(R.id.tvMedicationRowDosage);


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
            viewContainer.tvName.setText(prescriptions.get(position).getName()); //[position]);
            viewContainer.tvDosage.setText(prescriptions.get(position).getDosage());
            //viewContainer.imageView.setImageResource(imageIds[position]);
            return rowView;
        }
}
