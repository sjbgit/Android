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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sbunke.activities.PatientMainActivity;
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
        private PatientPrescriptionsArrayAdapter adapter;

        public PatientPrescriptionsArrayAdapter(
                Activity context, List<Prescription> prescriptions) {
            super(context, R.layout.patient_prescription_row_layout, prescriptions);
            this.context = context;
            this.prescriptions = prescriptions;
            adapter = this;
            //this.imageIds = imageIds;
        }

        static class ViewContainer {
            //public ImageView imageView;
            public TextView tvName;
            public TextView tvDosage;
            public Button btnEdit;
            public Button btnDelete;
        }

        @Override
        public View getView(final int position, View view, ViewGroup parent) {
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
                viewContainer.btnEdit = (Button)
                        rowView.findViewById(R.id.btnEditPrescription);
                viewContainer.btnDelete = (Button)
                        rowView.findViewById(R.id.btnDeletePrescription);



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

            final Prescription prescription = prescriptions.get(position);

            //---customize the content of each row based on position---
            viewContainer.tvName.setText(prescriptions.get(position).getName()); //[position]);
            viewContainer.tvDosage.setText(prescriptions.get(position).getDosage());

            viewContainer.btnEdit.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Toast.makeText(context,
                            "Edit Script",
                            Toast.LENGTH_SHORT).show();

                    final Dialog dialog = new Dialog(context);
                    // Include dialog.xml file
                    dialog.setContentView(R.layout.new_prescription_dialog_layout);
                    // Set dialog title
                    dialog.setTitle("Edit Prescription");

                    // set values for custom dialog components - text, image and button

                    final TextView medName = (TextView) dialog.findViewById(R.id.etMedicationName);
                    final TextView medDosage = (TextView) dialog.findViewById(R.id.etDosage);
                    medName.setText(prescription.getName());
                    medDosage.setText(prescription.getDosage());

                    final Button updateButton = (Button)dialog.findViewById(R.id.btnAddPrescription);
                    updateButton.setText("Update");

                    //text.setText("Custom dialog Android example.");
                    //ImageView image = (ImageView) dialog.findViewById(R.id.imageDialog);
                    //image.setImageResource(R.drawable.image0);

                    dialog.show();

                    Button declineButton = (Button) dialog.findViewById(R.id.btnCancelAddPrescription);
                    // if decline button is clicked, close the custom dialog
                    declineButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // Close dialog
                            dialog.dismiss();
                        }
                    });

                    Button addButton = (Button) dialog.findViewById(R.id.btnAddPrescription);
                    // if decline button is clicked, close the custom dialog
                    addButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            prescription.setName(medName.getText().toString());
                            prescription.setDosage(medDosage.getText().toString());

                            //Prescription p = new Prescription(medName.getText().toString(), medDosage.getText().toString());
                            //prescriptions.add(p);
                            adapter.notifyDataSetChanged();
                            // Close dialog
                            dialog.dismiss();
                        }
                    });

                }
            });

            viewContainer.btnDelete.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    prescriptions.remove(position);
                    adapter.notifyDataSetChanged();

                    Toast.makeText(context,
                            "Delete Script",
                            Toast.LENGTH_SHORT).show();
                }
            });

            //viewContainer.imageView.setImageResource(imageIds[position]);
            return rowView;
        }
}
