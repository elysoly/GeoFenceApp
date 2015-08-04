package com.example.internship.geofenceapp.Database;

import com.orm.SugarRecord;

import java.util.Date;

/**
 * Created by Mehran on 8/1/2015.
 */
public class GeofenceLogTable extends SugarRecord<GeofenceLogTable> {
    private LocationTable locationTable;
    private Date date;
    private String transitionDetails;
    private int transitionType;

    public GeofenceLogTable()
    {

    }

    public GeofenceLogTable(LocationTable locationTable , Date date , String transitionDetails , int transitionType)
    {
        this.locationTable = locationTable;
        this.date = date;
        this. transitionDetails = transitionDetails;
        this.transitionType = transitionType;
    }

    public void setLocationTable(LocationTable locationTable)
    {
        this.locationTable = locationTable;
    }
    public void setDate(Date date )
    {
        this.date = date;
    }
    public void setTransitionDetails(String transitionDetails)
    {
        this.transitionDetails = transitionDetails;
    }
    public void setTransitionType (int transitionType)
    {
        this.transitionType = transitionType;
    }

    public LocationTable getLocationTable(){return locationTable;}
    public Date getDate(){return date;}
    public String getTransitionDetails(){return transitionDetails;}
    public int getTransitionType(){return transitionType;}


















}
