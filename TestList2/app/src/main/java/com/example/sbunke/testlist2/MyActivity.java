package com.example.sbunke.testlist2;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.InputStream;


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

    public static String GET(String url){
        InputStream inputStream = null;
        String result = "";
        try {

            // create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // make GET request to the given URL
            HttpResponse httpResponse = httpclient.execute(new HttpGet(url));

            // receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // convert inputstream to string
           // if(inputStream != null)
                //result = convertInputStreamToString(inputStream);
            //else
             //   result = "Did not work!";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        return result;
    }

    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            return GET(urls[0]);
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getBaseContext(), "Received!", Toast.LENGTH_LONG).show();
            //etResponse.setText(result);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, presidents);

        ctx = this;

        new HttpAsyncTask().execute("http://10.41.4.140:8080/patients/all"); //"http://10.41.4.140:3333/"); //"http://10.41.1.1:8080/patients/all"); //"http://hmkcode.com/examples/index.php");


        HttpClient httpclient = new DefaultHttpClient();

        try {
            //HttpResponse httpResponse = httpclient.execute(new HttpGet("http://10.0.0.2:8080/patients/all"));
        }
        catch (Exception ex) {
            Exception e = ex;
        }

        // make GET request to the given URL


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

        Button buttonPie = (Button)findViewById(R.id.btnPieChart);
        buttonPie.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Intent launchIntent =
                        new Intent(ctx, TestDrawPieChart1.class);
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
