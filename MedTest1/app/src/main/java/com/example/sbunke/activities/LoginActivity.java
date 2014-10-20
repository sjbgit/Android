package com.example.sbunke.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.sbunke.models.User;
import com.example.sbunke.models.UserCredentials;


public class LoginActivity extends Activity {

    public static final String USER_NAME = "USER_NAME";
    public static final String USER_PASSWORD = "USER_PASSWORD";

    private Activity ctx;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferences = getPreferences(Activity.MODE_PRIVATE);

        ctx = this;
        handleButtons();
    }

    private void handleButtons() {
        //Button patientMain =

        ((Button)findViewById(R.id.btnLogin)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //get user credentials from prefs, if not there then
                UserCredentials userCredentials = getUserCredentials();

                //get from screen



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

    private void handleLogin() {

    }

    private void retrieveFromUiValidateAndStoreUserCredentials() {
        String userName = ((TextView)findViewById(R.id.tvUserName)).getText().toString();
        String password = ((TextView)findViewById(R.id.tvPassword)).getText().toString();
        saveUserCredentials(new UserCredentials(userName,password));

    }

    private UserCredentials getUserCredentials() {
        return new UserCredentials(sharedPreferences.getString(LoginActivity.USER_NAME, ""), sharedPreferences.getString(LoginActivity.USER_PASSWORD, ""));
    }

    private void saveUserCredentials(UserCredentials userCredentials) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(LoginActivity.USER_NAME, userCredentials.getUserName());
        editor.putString(LoginActivity.USER_PASSWORD, userCredentials.getPassword());
        editor.commit();
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
