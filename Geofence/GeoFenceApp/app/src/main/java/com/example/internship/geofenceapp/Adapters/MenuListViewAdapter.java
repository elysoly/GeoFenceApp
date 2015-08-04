package com.example.internship.geofenceapp.Adapters;


import android.app.Activity;
import android.content.Context;

import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;

import android.widget.TextView;
import android.widget.Toast;

import com.example.internship.geofenceapp.Database.DatabaseHandler;
import com.example.internship.geofenceapp.Database.LocationTable;
import com.example.internship.geofenceapp.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Mehran on 8/1/2015.
 */
public class MenuListViewAdapter extends ArrayAdapter {
    Context context;
    LocationTable locationTable;
    DatabaseHandler handler;
    int counter;

    public MenuListViewAdapter(Context context) {
        super(context,0);
        handler = new DatabaseHandler();
        this.context = context;
        counter  =0 ;
    }
    // by this methood, number of listview items could be specified
    public int getCount() {
        return handler.getLocationTableSize();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(R.layout.menulistgriditem, parent, false);

            ImageButton imageButton = (ImageButton) row.findViewById(R.id.image_button);
            TextView textViewTitle = (TextView) row.findViewById(R.id.textView);
            TextView textViewTitle2 = (TextView) row.findViewById(R.id.location_name);
            TextView textViewTitle3 = (TextView) row.findViewById(R.id.location_api_id);

            imageButton.setImageResource(R.drawable.ic_launcher);
            locationTable = handler.locationSendOut(position);
            if (locationTable != null) {
                textViewTitle.setText("P: " + position);
                if(imageButton != null){
                    imageButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.e("imageButton" , " is clicked befor toast");
                            Toast.makeText(context, "button in clicked", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                textViewTitle2.setText(locationTable.getLocationName()) ;
                textViewTitle3.setText("sample");
                textViewTitle3.setText("sample");
            } else {
                textViewTitle.setText("null");
                textViewTitle2.setText("null");
                textViewTitle3.setText("null");
            }
        }
        return row;
    }



}
