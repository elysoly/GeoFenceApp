package com.example.internship.geofenceapp.Database;
import com.google.android.gms.maps.model.LatLng;

import java.util.Calendar;
import java.util.Iterator;
/**
 * Created by Mehran on 8/1/2015.
 */
public class DatabaseHandler {
    public static int numberOfPlaceAdded;


    // Constructor of DatabaseHandler
    public DatabaseHandler() {
        numberOfPlaceAdded = LocationTable.listAll(LocationTable.class).size();
    }

    public LocationTable locationSendOut(int position) {
        Iterator<LocationTable> iterator = LocationTable.listAll(LocationTable.class).iterator();
        LocationTable locationTable;
        while(iterator.hasNext())
        {
            locationTable = iterator.next();
            if(locationTable != null) {
                if ((locationTable.getPriorityOnView() - 1) == position) {
                    return locationTable;
                }
            }
        }
        return null;
    }

    //Methods to handle with LocationTable entity
    public void addPlace(String locationName , LatLng locationLatlng , float locationRadius) {
        LocationTable locationTable = new LocationTable(locationName ,locationLatlng , locationRadius , numberOfPlaceAdded + 1);
        locationTable.save();
        numberOfPlaceAdded++;
    }
    public void removePlace(String location_name , LatLng locationLatLng) {
        Iterator<LocationTable> iterator = LocationTable.listAll(LocationTable.class).iterator();
        LocationTable locationTable;
        boolean remove = false;
        while(iterator.hasNext())
        {
            locationTable = iterator.next();
            if(locationTable.getLocationName().equalsIgnoreCase(location_name) &&
                    locationTable.getLocationLatLng().equals(locationLatLng))
            {
                removeGeofences(locationTable);
                locationTable.delete();
                remove = true;
                break;
            }
        }
        if(numberOfPlaceAdded > 0 && remove)
        {
            numberOfPlaceAdded--;
        }
    }
    public LocationTable getPlace(String location_name , LatLng locationLatLng) {
        Iterator<LocationTable> iterator = LocationTable.listAll(LocationTable.class).iterator();
        LocationTable locationTable;
        while(iterator.hasNext())
        {
            locationTable = iterator.next();
            if(locationTable.getLocationName().equalsIgnoreCase(location_name) &&
                    locationTable.getLocationLatLng().equals(locationLatLng))
            {
                return locationTable;
            }
        }
        return null;
    }

    //Methods to handel with GeofenceLogTable
    public void addGeofence(String location_name , LatLng locationLatLng , int transition_type , String transition_detail , float locationRadius) {
        LocationTable locationTable = getPlace(location_name, locationLatLng);
        Calendar c = Calendar.getInstance();
        if(locationTable != null)
        {
            GeofenceLogTable geofenceLogTable = new GeofenceLogTable(locationTable , c.getTime(), transition_detail , transition_type);
            geofenceLogTable.save();
        }else
        {
            addPlace(location_name,locationLatLng , locationRadius );
            GeofenceLogTable geofenceLogTable = new GeofenceLogTable(getPlace(location_name , locationLatLng) , c.getTime() , transition_detail,transition_type );
            geofenceLogTable.save();
        }
    }
    public void removeGeofences(LocationTable locationTable ) {
        Iterator<GeofenceLogTable> iterator = GeofenceLogTable.listAll(GeofenceLogTable.class).iterator();
        GeofenceLogTable geofenceLogTable;
        boolean remove = false;
        if(locationTable != null)
        {
            while(iterator.hasNext())
            {
                geofenceLogTable = iterator.next();
                if( (geofenceLogTable.getLocationTable().equals(locationTable)))
                {
                    geofenceLogTable.delete();
                    remove = true;
                }
            }
        }else
        {
            return;
        }
    }
    public GeofenceLogTable getGeofence(LocationTable locationTable) {
        if (locationTable != null) {
            Iterator<GeofenceLogTable> iterator = GeofenceLogTable.listAll(GeofenceLogTable.class).iterator();
            GeofenceLogTable geofenceLogTable;
            while(iterator.hasNext())
            {
                geofenceLogTable = iterator.next();
                if(geofenceLogTable.getLocationTable().equals(locationTable) )
                {
                    return geofenceLogTable;
                }
            }
        }
        return null;
    }

    public int getLocationTableSize()
    {
        return LocationTable.listAll(LocationTable.class).size();
    }
}
