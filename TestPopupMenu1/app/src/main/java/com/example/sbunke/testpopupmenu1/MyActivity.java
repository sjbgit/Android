package com.example.sbunke.testpopupmenu1;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MyActivity extends Activity {

    private static final String[] ITEMS =
            {"Mom", "Dad", "Brother", "Sister", "Uncle", "Aunt"};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Register a button for context events
        ListView list = new ListView(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, ITEMS);
        list.setAdapter(adapter);
        registerForContextMenu(list);

        setContentView(list);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.contextmenu, menu);
        menu.setHeaderTitle("Choose an Option");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        //You can obtain the item that was clicked from the
        // bundled ContextMenuInfo object, which is an instance
        // of AdapterContextMenuInfo in the case of a ListView
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int listPosition = info.position;

        //Switch on the item's ID to find the action the user selected
        switch(item.getItemId()) {
            case R.id.menu_delete:
                //Perform delete actions
                break;
            case R.id.menu_edit:
                //Perform edit actions
                break;
            default:
                return super.onContextItemSelected(item);
        }
        return true;
    }
}
