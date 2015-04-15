package com.iwebirth.interact.model;
/**
 * 终端与littlesever通信
 * 终端位置信息类
 * @author YY_410
 * 2015-1-15
 * **/
public class TerminalLocationInfo {

	String tId;
	String lat;
	String lng;

    public String gettId() {
        return tId;
    }

    public void settId(String tId) {
        this.tId = tId;
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

    public TerminalLocationInfo(String tId, String tLatitude, String tLongitude) {
        this.tId = tId;
        this.lat = tLatitude;
        this.lng = tLongitude;
    }
}
