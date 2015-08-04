package com.example.internship.geofenceapp;

/**
 * Created by Mehran on 8/1/2015.
 */



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class GeoFenceMap extends ActionBarActivity implements OnMapReadyCallback {

    GoogleMap m_map;
    boolean mapReady=false;
    Context context=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        setTitle("Map");
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap map){
        mapReady=true;
        m_map = map;
        m_map.setMyLocationEnabled(true);
        m_map.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng point) {
                m_map.clear();
                m_map.addMarker(new MarkerOptions()
                        .position(point)
                        .title("Hello world"));

                m_map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(LatLng latLng) {

                        m_map.clear();

                    }
                });

                final Dialog dialog = new Dialog(context);
                dialog.setTitle("New Place");
                dialog.show();




            }
        });
        LatLng newYork = new LatLng(35.734809,51.414130);
        CameraPosition target = CameraPosition.builder().target(newYork).zoom(17).bearing(111).tilt(65).build();
        m_map.moveCamera(CameraUpdateFactory.newCameraPosition(target));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_map, menu);
        return true;
    }

        @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onStop() {
        super.onStop();
        Log.i("geofence","top " );
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("geofence","pause");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent back=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(back);

    }



}


