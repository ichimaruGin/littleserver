package com.iwebirth.interact.model;

/**
 * Created by YY_410 on 2015/4/15.
 */
public class IntimeInfo extends TerminalBase{

    public String tId;

    public int speed;

    public int rotate;

    public int waterTemperature;

    public String lat;

    public String lng;

    public IntimeInfo(String tId, int speed, int waterTemperature, String lat, String lng, int rotate) {
        this.tId = tId;
        this.speed = speed;
        this.waterTemperature = waterTemperature;
        this.lat = lat;
        this.lng = lng;
        this.rotate = rotate;
    }
}
