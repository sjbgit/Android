package com.example.sbunke.adapters;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.sbunke.activities.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by sbunke on 10/31/2014.
 */
public class PatientAlarmTimePickerArrayAdapter extends ArrayAdapter<Date> {

    public static SimpleDateFormat formatter = new SimpleDateFormat("HH:mm a");
    private final Activity context;
    private List<Date> dates; // = new ArrayList<Patient>();
    private ArrayAdapter adapterClass;

    public PatientAlarmTimePickerArrayAdapter(Activity context, List<Date> dates){
        super(context, R.layout.patient_check_in_alarm_timepicker_dialog_row_layout, dates);
        this.context = context;
        this.dates = dates;

        this.adapterClass = this;

    }

    static class ViewContainer {
        public Button btnEditTime;
        public Button btnDeleteTime;
        public TextView tvAlarmTime;

    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewContainer viewContainer;
        View rowView = view;

        //---print the index of the row to examine---
        Log.d("PatientAlarmArrayAdapter", String.valueOf(position));

        //---if the row is displayed for the first time---
        if (rowView == null) {

            Log.d("PatientAlarmArrayAdapter", "New");
            LayoutInflater inflater = context.getLayoutInflater();
            rowView = inflater.inflate(R.layout.patient_check_in_alarm_timepicker_dialog_row_layout, null, true);

            //---create a view container object---
            viewContainer = new ViewContainer();

            //---get the references to all the views in the row---
            viewContainer.btnEditTime = (Button)
                    rowView.findViewById(R.id.btnEditAlarmTime);

            viewContainer.btnDeleteTime = (Button)
                    rowView.findViewById(R.id.btnDeleteAlarm);

            viewContainer.tvAlarmTime = (TextView)rowView.findViewById(R.id.tvAlarmTime);

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

        final int pos = position;

        viewContainer.btnEditTime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {

                        Date date = dates.get(pos);

                        date.setHours(selectedHour);
                        date.setMinutes(selectedMinute);

                        //viewContainer.tvAlarmTime.setText(date.getHours() + ":" + date.getMinutes());

                        //eReminderTime.setText( selectedHour + ":" + selectedMinute);
                        Toast.makeText(context,
                                "Selected hour " + selectedHour + " selected minute " + selectedMinute ,
                                Toast.LENGTH_SHORT).show();

                        adapterClass.notifyDataSetChanged();


                    }
                }, dates.get(pos).getHours(), dates.get(pos).getMinutes(), true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }

        });

        Date date = dates.get(position);
        viewContainer.tvAlarmTime.setText(formatter.format(date)); //date.getHours() + ":" + date.getMinutes());

        //---customize the content of each row based on position---
                //viewContainer.timePicker.setCurrentHour(dates.get(position).getHours()); //[position]);
                //viewContainer.timePicker.setCurrentMinute(dates.get(position).getMinutes());// + " ...Some descriptions here...");
                //viewContainer.imageView.setImageResource(imageIds[position]);
        return rowView;
    }

}