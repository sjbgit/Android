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
import com.example.sbunke.helpers.SharedPreferencesHelper;
import com.example.sbunke.models.Patient;
import com.example.sbunke.viewmodels.PatientCheckInViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by sbunke on 11/13/2014.
 */
public class PhysicianCheckInArrayAdapter extends ArrayAdapter<PatientCheckInViewModel> {

    List<PatientCheckInViewModel> checkIns;
    private final Activity context;
    public PhysicianCheckInArrayAdapter(
            Activity context, List<PatientCheckInViewModel> checkIns) {
        super(context, R.layout.patient_checkin_row_layout, checkIns);
        this.context = context;
        this.checkIns = checkIns;
        //this.originalPatientList = patients;
        //this.imageIds = imageIds;
    }

    static class ViewContainer {
        public ImageView imageView;
        public TextView tvCheckInInfo;
        public TextView tvMoreCheckInInfo;
        public TextView tvCheckInDateTime;
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
            rowView = inflater.inflate(R.layout.patient_checkin_row_layout, null, true);

            //---create a view container object---
            viewContainer = new ViewContainer();

            //---get the references to all the views in the row---
            viewContainer.tvCheckInDateTime = (TextView)
                    rowView.findViewById(R.id.tvCheckInDateTime);
            viewContainer.tvCheckInInfo = (TextView)
                    rowView.findViewById(R.id.tvCheckInInfo);
            viewContainer.tvMoreCheckInInfo = (TextView)
                    rowView.findViewById(R.id.tvMoreCheckInInfo);
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

        SimpleDateFormat formatter = SharedPreferencesHelper.formatter;

        //---customize the content of each row based on position---
        viewContainer.tvCheckInDateTime.setText(formatter.format(checkIns.get(position).checkInDate));
        //Date d = checkIns.get(position).checkInDate;
        viewContainer.tvCheckInInfo.setText(checkIns.get(position).mainInformation); //[position]);
        viewContainer.tvMoreCheckInInfo.setText(checkIns.get(position).secondaryInformation + " ...Some descriptions here...");
        //viewContainer.imageView.setImageResource(imageIds[position]);
        return rowView;
    }


}



