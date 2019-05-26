package com.adamk.watermark.entities;

import java.util.Date;

public class Report {
    private double phValue; // 6.7 optimal
    private double turbidity; //NTU below 3 is nice
    private double tempCelsius;
    private double dissolvedOxygen; //mg per litre or ppm, between 5 and 10 is nice

    private boolean isSafeToDrink;

    private Date date;

    public Report(double phValue, double turbidity, double tempCelsius, double dissolvedOxygen, boolean isSafeToDrink, Date date) {
        this.phValue = phValue;
        this.turbidity = turbidity;
        this.tempCelsius = tempCelsius;
        this.dissolvedOxygen = dissolvedOxygen;
        this.isSafeToDrink = isSafeToDrink;
        this.date = date;
    }
}
