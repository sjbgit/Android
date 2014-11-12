package com.example.sbunke.adapters;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.sbunke.activities.R;
import com.example.sbunke.models.Patient;

import java.util.Date;
import java.util.List;

/**
 * Created by sbunke on 10/31/2014.
 */
public class PatientAlarmArrayAdapter extends ArrayAdapter<Date> {

    private final Activity context;
    private List<Date> dates; // = new ArrayList<Patient>();
    private PatientAlarmArrayAdapter patientAlarmArrayAdapter;

    public PatientAlarmArrayAdapter(Activity context, List<Date> dates){
        super(context, R.layout.patient_check_in_alarm_row_layout, dates);
        this.context = context;
        this.dates = dates;
        this.patientAlarmArrayAdapter = this;

    }

    static class ViewContainer {
        public TimePicker timePicker;
        public Button deleteButton;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        ViewContainer viewContainer;
        View rowView = view;

        //---print the index of the row to examine---
        Log.d("PatientAlarmArrayAdapter", String.valueOf(position));

        //---if the row is displayed for the first time---
        if (rowView == null) {

            Log.d("PatientAlarmArrayAdapter", "New");
            LayoutInflater inflater = context.getLayoutInflater();
            rowView = inflater.inflate(R.layout.patient_check_in_alarm_row_layout, null, true);

            //---create a view container object---
            viewContainer = new ViewContainer();

            //---get the references to all the views in the row---
            viewContainer.timePicker = (TimePicker)
                    rowView.findViewById(R.id.tpAlarmTimePicker);

            viewContainer.deleteButton = (Button)rowView.findViewById(R.id.btnDeleteAlarm);

            viewContainer.deleteButton.setOnClickListener(new View.OnClickListener() {
                                                              public void onClick(View v) {
                                                                    dates.remove(position);
                                                                  patientAlarmArrayAdapter.notifyDataSetChanged();
                                                              }
                                                          });


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
        viewContainer.timePicker.setCurrentHour(dates.get(position).getHours()); //[position]);
        viewContainer.timePicker.setCurrentMinute(dates.get(position).getMinutes());// + " ...Some descriptions here...");
        //viewContainer.imageView.setImageResource(imageIds[position]);
        return rowView;
    }

}
