package com.example.sbunke.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class PatientMainActivity extends Activity {

    private Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_main);

        ctx = this;

        ((Button)findViewById(R.id.btnSetCheckinAlarms)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent launchIntent =
                        new Intent(ctx, CheckInAlarmsActivity.class);
                ctx.startActivity(launchIntent);
                //PendingIntent contentIntent =
                //        PendingIntent.getActivity(ctx, 0, launchIntent, 0);
                //ctx.startActivity(launchIntent);
            }
        });

        ((Button)findViewById(R.id.btnPatientCheckIn)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent launchIntent =
                        new Intent(ctx, PatientCheckInActivity.class);
                ctx.startActivity(launchIntent);
                //PendingIntent contentIntent =
                //        PendingIntent.getActivity(ctx, 0, launchIntent, 0);
                //ctx.startActivity(launchIntent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.patient_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
