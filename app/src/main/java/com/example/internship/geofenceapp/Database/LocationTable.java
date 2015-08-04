package com.example.internship.geofenceapp.Database;

import com.orm.SugarRecord;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Mehran on 8/1/2015.
 */
public class LocationTable extends SugarRecord<LocationTable> {
    private String locationName;
    private float locationRadius;
    private int priorityOnView;
    private LatLng locationLatLng;
    public LocationTable(){

    }

    public LocationTable(String locationName ,LatLng locationLatLng , float locationRadius, int priorityOnView)
    {
        this.locationName = locationName;
        this.locationLatLng = locationLatLng;
        this.locationRadius = locationRadius;
        this.priorityOnView = priorityOnView;
    }

    public void setLocationLatLng(LatLng locationLatLng)
    {
        this.locationLatLng = locationLatLng;
    }
    public void setPriorityOnView(int priorityOnView)
    {
        this.priorityOnView = priorityOnView;
    }
    public void setLocationName(String locationNamee)
    {
        this.locationName = locationNamee;
    }
    public void setLocationRadius(float locationRadius)
    {
        this.locationRadius = locationRadius;
    }

    public String getLocationName(){return locationName;}
    public float getLocationRadius(){return locationRadius;}
    public int getPriorityOnView(){return priorityOnView;}
    public LatLng getLocationLatLng(){return locationLatLng;}
}
