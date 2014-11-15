package com.example.sbunke.adapters;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.sbunke.activities.PickDateTimeActivity;
import com.example.sbunke.activities.R;
import com.example.sbunke.helpers.DateAndTimePickerHelper;
import com.example.sbunke.helpers.SharedPreferencesHelper;
import com.example.sbunke.models.Patient;
import com.example.sbunke.viewmodels.PrescriptionCheckInViewModel;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by sbunke on 10/23/2014.
 */
public class PatientCheckInArrayAdapter extends ArrayAdapter<PrescriptionCheckInViewModel> {

    private final Activity context;
    private List<PrescriptionCheckInViewModel> prescriptionCheckInViewModels; // = new ArrayList<Patient>();
    //protected final Integer[] imageIds;

    private PatientCheckInArrayAdapter patientCheckInArrayAdapter;

    public PatientCheckInArrayAdapter(
            Activity context, List<PrescriptionCheckInViewModel> prescriptionCheckInViewModels) {
        super(context, R.layout.patient_checkin_medication_row_layout, prescriptionCheckInViewModels);
        this.context = context;
        this.prescriptionCheckInViewModels = prescriptionCheckInViewModels;
        patientCheckInArrayAdapter = this;
        //this.imageIds = imageIds;
    }

    static class ViewContainer {
        //public ImageView imageView;
        //public TextView firstNameTv;
        public TextView tvPrescriptionTakenQuestion;
        public RadioButton rbMedicationWasTaken;
        public RadioGroup rbgMedicationTaken;
        public TextView tvPrescriptionTakenDate;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final ViewContainer viewContainer;
        View rowView = view;

        //---print the index of the row to examine---
        Log.d("PatientArrayAdapter", String.valueOf(position));

        final int pos = position;

        //---if the row is displayed for the first time---
        if (rowView == null) {

            Log.d("PatientArrayAdapter", "New");
            LayoutInflater inflater = context.getLayoutInflater();
            rowView = inflater.inflate(R.layout.patient_checkin_medication_row_layout, null, true);

            //---create a view container object---
            viewContainer = new ViewContainer();

            //---get the references to all the views in the row---
            viewContainer.tvPrescriptionTakenQuestion = (TextView)
                    rowView.findViewById(R.id.tvPrescriptionTakenQuestion);//tvPrescriptionTakenDate

            viewContainer.tvPrescriptionTakenDate = (TextView)
                    rowView.findViewById(R.id.tvPrescriptionTakenDate);

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
            Log.d("PatientArrayAdapter", "Recycling");
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

                    promptForDateAndTime("When did you take your " + prescriptionCheckInViewModels.get(pos).getPrescriptionName() + "?", prescriptionCheckInViewModels.get(pos), viewContainer.tvPrescriptionTakenDate);
                    /*
                    Intent launchIntent =
                            new Intent(context, PickDateTimeActivity.class);
                    launchIntent.putExtra(PickDateTimeActivity.MESSAGE_KEY, "When did you take your " + prescriptionCheckInViewModels.get(pos).getPrescriptionName());
                    context.startActivityForResult(launchIntent, REQUEST_CODE);
                    */

                    patientCheckInArrayAdapter.notifyDataSetChanged();

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

    private Date promptForDateAndTime(String message, PrescriptionCheckInViewModel viewModel, TextView tv) {
        final Dialog dialog = new Dialog(context);
        // Include dialog.xml file
        dialog.setContentView(R.layout.enter_checkin_date_and_time_layout);
        // Set dialog title
        dialog.setTitle("Enter Date and Time");

        final TextView tv1 = tv;

        // set values for custom dialog components - text, image and button

        final PrescriptionCheckInViewModel vm = viewModel;

        final TimePicker timePicker = (TimePicker) dialog.findViewById(R.id.tpTimePicker);
        final DatePicker datePicker = (DatePicker) dialog.findViewById(R.id.dpDatePicker);
        final Date now = new Date();
        timePicker.setCurrentHour(now.getHours());
        timePicker.setCurrentMinute(now.getMinutes());
        datePicker.setMaxDate(now.getTime());

        Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);


        final TextView tvMessage = (TextView)dialog.findViewById(R.id.tvPrescriptionTakenMessage);
        tvMessage.setText(message);


        //medName.setText(prescription.getName());
        //medDosage.setText(prescription.getDosage());

        //final Button updateButton = (Button)dialog.findViewById(R.id.btnAddPrescription);
        //updateButton.setText("Update");

        //text.setText("Custom dialog Android example.");
        //ImageView image = (ImageView) dialog.findViewById(R.id.imageDialog);
        //image.setImageResource(R.drawable.image0);

        dialog.show();

        /*
        Button declineButton = (Button) dialog.findViewById(R.id.btnCancelAddPrescription);
        // if decline button is clicked, close the custom dialog
        declineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Close dialog
                dialog.dismiss();
            }
        });
        */
        Button addButton = (Button) dialog.findViewById(R.id.btnDateTimeSelected);
        // if decline button is clicked, close the custom dialog
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Date pickedDate = DateAndTimePickerHelper.getDateFromDatePickerAndTimePicker(datePicker, timePicker);
                vm.setDateTaken(pickedDate);
                tv1.setText(SharedPreferencesHelper.formatter.format(pickedDate));
                Toast.makeText(context,
                        SharedPreferencesHelper.formatter.format(pickedDate),
                        Toast.LENGTH_SHORT).show();

                //prescription.setName(medName.getText().toString());
                //prescription.setDosage(medDosage.getText().toString());

                //Prescription p = new Prescription(medName.getText().toString(), medDosage.getText().toString());
                //prescriptions.add(p);
                //adapter.notifyDataSetChanged();
                // Close dialog
                dialog.dismiss();
            }
        });

        //TODO: FIX THIS
        return now;

    }

}