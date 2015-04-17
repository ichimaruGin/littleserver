package com.iwebirth.interact.model;
/**
 * 终端与littlesever通信
 * 终端位置信息类
 * @author YY_410
 * 2015-1-15
 * **/
public class TerminalLocationInfo extends TerminalBase{

	public String tId;

	public String lat;

	public String lng;

    public TerminalLocationInfo(String tId, String lat, String lng) {
        this.tId = tId;
        this.lat = lat;
        this.lng = lng;
    }
}
