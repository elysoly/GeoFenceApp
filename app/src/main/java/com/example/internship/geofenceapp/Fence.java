package com.example.internship.geofenceapp;

import android.app.PendingIntent;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.internship.geofenceapp.Database.DatabaseHandler;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by ely on 15/08/04.
 */
public class Fence implements  GoogleApiClient.OnConnectionFailedListener , LocationListener, GoogleApiClient.ConnectionCallbacks, ResultCallback<Status> {

    private boolean WiFi;
    private boolean Silent;
    private String FenceName;
    private int radius;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;


    @Override
    public void onConnected(Bundle bundle)
    {

//        mLocationRequest =  LocationRequest.create();
//        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
//        mLocationRequest.setInterval(1000);
//        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }



    public Geofence addFenc(LatLng Loc , int radius)
    {
       Geofence g =  new Geofence.Builder()
                 .setRequestId(String.valueOf(DatabaseHandler.numberOfPlaceAdded + 1))//unique ID!
                 .setCircularRegion(Loc.latitude, Loc.longitude, radius)
                .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER | Geofence.GEOFENCE_TRANSITION_EXIT)
                .build();

        GeofencingRequest.Builder builder = new GeofencingRequest.Builder();
        builder.setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER);
        builder.addGeofence(g);
        GeofencingRequest  req=builder.build();

        if (!mGoogleApiClient.isConnected()) {
            //Toast.makeText(this, (R.string.not_connected), Toast.LENGTH_SHORT).show();
            Log.i("Fence" , "Google API is not connected!");
            return null;
        }

        try {
            LocationServices.GeofencingApi.addGeofences(
                    mGoogleApiClient,
                    // The GeofenceRequest object.
                    req,
                    // A pending intent that that is reused when calling removeGeofences(). This
                    // pending intent is used to generate an intent when a matched geofence
                    // transition is observed.
                    getGeofencePendingIntent()
            ).setResultCallback(this); // Result processed in onResult().
        } catch (SecurityException securityException) {
            // Catch exception generated if the app does not use ACCESS_FINE_LOCATION permission.
        }

        return g;
    }



    private PendingIntent getGeofencePendingIntent() {
        Intent intent = new Intent(this, GeofenceTransitionsIntentService.class);
        // We use FLAG_UPDATE_CURRENT so that we get the same pending intent back when calling addgeoFences()
        return PendingIntent.getService(, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }


    @Override
    public void onResult(Status status) {

    }
}


