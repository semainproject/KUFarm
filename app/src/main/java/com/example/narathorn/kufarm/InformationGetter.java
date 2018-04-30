package com.example.narathorn.kufarm;

/**
 * Created by narathorn on 15/2/2018 AD.
 */

public class InformationGetter {
    double tempAir, humidAir, humidSoil, ph, uv;
    String date, time, soil;
    public InformationGetter() {

    }

    public InformationGetter(double tempAir, double humidAir, double humidSoil, double ph, double uv, String date, String time, String soil) {
        this.tempAir = tempAir;
        this.humidAir = humidAir;
        this.humidSoil = humidSoil;
        this.ph = ph;
        this.uv = uv;
        this.date = date;
        this.time = time;
        this.soil = soil;
    }

    public double getTempAir() {
        return tempAir;
    }

    public double getHumidAir() {
        return humidAir;
    }

    public double getHumidSoil() {
        return humidSoil;
    }

    public double getPh() {
        return ph;
    }

    public double getUv() {
        return uv;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getSoil() {
        return soil;
    }
}
