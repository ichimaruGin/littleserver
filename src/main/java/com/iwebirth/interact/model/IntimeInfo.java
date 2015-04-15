package com.iwebirth.interact.model;

/**
 * Created by YY_410 on 2015/4/15.
 */
public class IntimeInfo {

    String tId;
    String speed;
    String rotate;
    String waterTemperature;
    String lat;
    String lng;

    public IntimeInfo(String tId, String rotate, String speed, String waterTemperature, String lat, String lng) {
        this.tId = tId;
        this.rotate = rotate;
        this.speed = speed;
        this.waterTemperature = waterTemperature;
        this.lat = lat;
        this.lng = lng;
    }

    public String gettId() {
        return tId;
    }

    public void settId(String tId) {
        this.tId = tId;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getRotate() {
        return rotate;
    }

    public void setRotate(String rotate) {
        this.rotate = rotate;
    }

    public String getWaterTemperature() {
        return waterTemperature;
    }

    public void setWaterTemperature(String waterTemperature) {
        this.waterTemperature = waterTemperature;
    }

    public String getLat() {

        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }
}
