package com.adamk.watermark.entities;

import java.util.ArrayList;

public class WaterSource {
    private ArrayList<Report> reports;
    private Location location;

    public WaterSource(double latitude, double longitude) {
        this.location = new Location(latitude, longitude);
        this.reports = new ArrayList<>();
    }

    public Location getLocation(){
        return location;
    }

    public void setLocation(double latitude, double longitude){
        this.location = new Location(latitude, longitude);
    }

    public Report getLastReport(){
        if(!reports.isEmpty()) {
            return reports.get(reports.size() - 1);
        }
        return null;
    }

    public void addReport(Report report){
        reports.add(report);
    }

}
