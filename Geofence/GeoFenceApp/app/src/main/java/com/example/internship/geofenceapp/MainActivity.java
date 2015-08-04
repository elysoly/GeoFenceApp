package com.example.internship.geofenceapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.example.internship.geofenceapp.Adapters.MenuListViewAdapter;


public class MainActivity extends ActionBarActivity {
    ListView listView;
    MenuListViewAdapter menuListViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        menuListViewAdapter = new MenuListViewAdapter(this);
        listView.setAdapter(menuListViewAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id)
        {
            case R.id.mapButton:
//                Toast.makeText(this , "MapButton is clicked" , Toast.LENGTH_SHORT).show();

                Intent map=new Intent(getApplicationContext(),GeoFenceMap.class);
                startActivity(map);
                break;
            case R.id.placeButton:
                Toast.makeText(this , "PlaceButton is clicked" , Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onStop() {
        super.onStop();
        Log.i("main", "top ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("main","pause");
    }
}
