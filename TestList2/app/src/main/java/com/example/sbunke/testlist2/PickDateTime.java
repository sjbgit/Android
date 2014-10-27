package com.example.sbunke.testlist2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;


public class PickDateTime extends Activity {

    private Context ctx;
    private Activity activity;
    public static final String MESSAGE_KEY = "MESSAGE_KEY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_date_time);
        activity = this;
        ctx = this;

        Bundle extras = getIntent().getExtras();
        String inputString = extras.getString(MESSAGE_KEY);

        TextView messageView = (TextView)findViewById(R.id.tvDateTimePickMessage);
        messageView.setText(inputString);

        Button button = (Button)findViewById(R.id.btnDateTimeSelected);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click

                Intent intent = new Intent();

                //String string = editText.getText().toString();
                DatePicker datePicker = (DatePicker)findViewById(R.id.dpDatePicker);
                int day = datePicker.getDayOfMonth();
                int month = datePicker.getMonth() + 1;
                int year = datePicker.getYear();

                String dateString = month + "/" + day + "/" + year;

                TimePicker timePicker = (TimePicker)findViewById(R.id.tpTimePicker);
                int hour = timePicker.getCurrentHour();
                int minute = timePicker.getCurrentMinute();

                String result = dateString + " - " + hour + ":" + minute;



                intent.putExtra(MESSAGE_KEY, result);
                setResult(RESULT_OK, intent);
                //super.finish();

                activity.finish();
                //String message=editTextMessage.getText().toString();
                //Intent intentMessage=new Intent();

                // put the message in Intent
                //intentMessage.putExtra(MESSAGE_KEY,"TEST");
                // Set The Result in Intent
                //setResult(2,intentMessage);
                // finish The activity
                //finish();

                //Intent launchIntent =
                //        new Intent(ctx, NotificationActivity.class);
                //PendingIntent contentIntent =
                //        PendingIntent.getActivity(ctx, 0, launchIntent, 0);
                //ctx.startActivity(launchIntent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.pick_date_time, menu);
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
