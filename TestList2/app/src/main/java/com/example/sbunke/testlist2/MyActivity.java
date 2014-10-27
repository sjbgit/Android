package com.example.sbunke.testlist2;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


public class MyActivity extends Activity {

    private static final int REQUEST_CODE = 10;
    Context ctx;
    ListView l0, l1, l2;
    String[] presidents = {
            "Dwight D. Eisenhower",
            "John F. Kennedy",
            "Lyndon B. Johnson",
            "Richard Nixon",
            "Gerald Ford",
            "Jimmy Carter",
            "Ronald Reagan",
            "George H. W. Bush",
            "Bill Clinton",
            "George W. Bush",
            "Barack Obama"
    };
    Integer[] imageIDs = {};

    /*
            R.drawable.pic1,
            R.drawable.pic2,
            R.drawable.pic3,
            R.drawable.pic4,
            R.drawable.pic5,
            R.drawable.pic6,
            R.drawable.pic7,
            R.drawable.pic8,
            R.drawable.pic9,
            R.drawable.pic10,
            R.drawable.pic11
    };
*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            if (data.hasExtra(PickDateTime.MESSAGE_KEY)) {
                String result = data.getExtras().getString(PickDateTime.MESSAGE_KEY);
                if (result != null && result.length() > 0) {
                    Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, presidents);

        ctx = this;

        //---List View---
        /*
        l1 = (ListView) findViewById(R.id.ListView1);
        l1.setAdapter(adapter);
        l1.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                int index = arg2;
                Toast.makeText(getBaseContext(),
                        "You have selected item : " + presidents[index],
                        Toast.LENGTH_SHORT).show();
            }
        });
        //---List View---
        l2 = (ListView) findViewById(R.id.ListView2);
        l2.setAdapter(adapter);
        l2.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                int index = arg2;
                Toast.makeText(getBaseContext(),
                        "You have selected item : " + presidents[index],
                        Toast.LENGTH_SHORT).show();
            }
        });
        */


        /*
        CustomArrayAdapter customAdapter = new
                CustomArrayAdapter(this, presidents, imageIDs);
        */


        //setListAdapter(adapter);

        //ArrayAdapter<String> userAdapter = new ArrayAdapter<String>(
        //        this, , presidents);

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent launchIntent =
                        new Intent(ctx, NotificationActivity.class);
                //PendingIntent contentIntent =
                //        PendingIntent.getActivity(ctx, 0, launchIntent, 0);
                ctx.startActivity(launchIntent);
            }
        });

        Button button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent launchIntent =
                        new Intent(ctx, PickDateTime.class);
                launchIntent.putExtra(PickDateTime.MESSAGE_KEY, "When did you take your OxyContin?");
                startActivityForResult(launchIntent, REQUEST_CODE);
                //PendingIntent contentIntent =
                //        PendingIntent.getActivity(ctx, 0, launchIntent, 0);
                //ctx.startActivity(launchIntent);
            }
        });



        AdvancedCustomArrayAdapter advancedCustomArrayAdapter =
                new AdvancedCustomArrayAdapter(this, presidents, imageIDs);

        l0 = (ListView) findViewById(R.id.ListView0);
        l0.setAdapter(advancedCustomArrayAdapter);
        l0.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                int index = arg2;
                Toast.makeText(getBaseContext(),
                        "You have selected item : " + presidents[index],
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

}
