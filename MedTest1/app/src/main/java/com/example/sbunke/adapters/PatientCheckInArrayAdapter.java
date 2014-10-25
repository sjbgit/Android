package com.example.sbunke.adapters;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sbunke.activities.R;
import com.example.sbunke.models.Patient;
import com.example.sbunke.viewmodels.PrescriptionCheckInViewModel;

import java.util.List;

/**
 * Created by sbunke on 10/23/2014.
 */
public class PatientCheckInArrayAdapter extends ArrayAdapter<PrescriptionCheckInViewModel> {

    private final Activity context;
    private List<PrescriptionCheckInViewModel> prescriptionCheckInViewModels; // = new ArrayList<Patient>();
    //protected final Integer[] imageIds;

    public PatientCheckInArrayAdapter(
            Activity context, List<PrescriptionCheckInViewModel> prescriptionCheckInViewModels) {
        super(context, R.layout.patient_checkin_medication_row_layout, prescriptionCheckInViewModels);
        this.context = context;
        this.prescriptionCheckInViewModels = prescriptionCheckInViewModels;
        //this.imageIds = imageIds;
    }

    static class ViewContainer {
        //public ImageView imageView;
        //public TextView firstNameTv;
        public TextView tvPrescriptionTakenQuestion;
        public RadioButton rbMedicationWasTaken;
        public RadioGroup rbgMedicationTaken;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final ViewContainer viewContainer;
        View rowView = view;

        //---print the index of the row to examine---
        Log.d("PatientArrayAdapter", String.valueOf(position));

        //---if the row is displayed for the first time---
        if (rowView == null) {

            Log.d("PatientArrayAdapter", "New");
            LayoutInflater inflater = context.getLayoutInflater();
            rowView = inflater.inflate(R.layout.patient_checkin_medication_row_layout, null, true);

            //---create a view container object---
            viewContainer = new ViewContainer();

            //---get the references to all the views in the row---
            viewContainer.tvPrescriptionTakenQuestion = (TextView)
                    rowView.findViewById(R.id.tvPrescriptionTakenQuestion);

            viewContainer.rbMedicationWasTaken = (RadioButton)rowView.findViewById(R.id.rbMedicationWasTaken);

            viewContainer.rbgMedicationTaken = (RadioGroup)rowView.findViewById(R.id.rbgMedicationTaken);

            /*
            viewContainer.lastNameTv = (TextView)
                    rowView.findViewById(R.id.patientLastNameTv);
            viewContainer.imageView = (ImageView)
                    rowView.findViewById(R.id.icon);
            */
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
        viewContainer.tvPrescriptionTakenQuestion.setText("Did you take your " + prescriptionCheckInViewModels.get(position).getPrescriptionName() + "?"); //[position]);

        viewContainer.rbgMedicationTaken.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (viewContainer.rbMedicationWasTaken.isChecked()){
                    Toast.makeText(context,
                            "Medication Taken",
                            Toast.LENGTH_SHORT).show();
                    //https://code.google.com/p/datetimepicker/source/browse/trunk/src/com/ptashek/widgets/datetimepicker/DateTimePicker.java
                }
            }
        });


        //viewContainer.rbMedicationWasTaken.on

        //viewContainer.lastNameTv.setText(prescriptionCheckInViewModels.get(position).getLastName() + " ...Some descriptions here...");
        //viewContainer.imageView.setImageResource(imageIds[position]);
        return rowView;
    }

}