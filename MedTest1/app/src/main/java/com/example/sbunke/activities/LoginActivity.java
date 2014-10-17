package com.example.sbunke.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class LoginActivity extends Activity {

    private Activity ctx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ctx = this;
        handleButtons();
    }

    private void handleButtons() {
        //Button patientMain =

        ((Button)findViewById(R.id.btnLogin)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(ctx);

                builder.setMessage(R.string.login_failed_message)
                        .setTitle(R.string.login_title);


                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK button
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();



                // Perform action on click
                //Intent launchIntent =
                //        new Intent(ctx, PatientMainActivity.class);
                //PendingIntent contentIntent =
                //        PendingIntent.getActivity(ctx, 0, launchIntent, 0);
                //ctx.startActivity(launchIntent);
            }
        });

        ((Button)findViewById(R.id.patientLoginButton)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent launchIntent =
                        new Intent(ctx, PatientMainActivity.class);
                //PendingIntent contentIntent =
                //        PendingIntent.getActivity(ctx, 0, launchIntent, 0);
                ctx.startActivity(launchIntent);
            }
        });

        ((Button)findViewById(R.id.physicianLoginButton)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent launchIntent =
                        new Intent(ctx, PhysicianMainActivity.class);
                //PendingIntent contentIntent =
                //        PendingIntent.getActivity(ctx, 0, launchIntent, 0);
                ctx.startActivity(launchIntent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
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
