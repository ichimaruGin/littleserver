package com.iwebirth.interact.model;
/**
 * 终端与littlesever通信
 * 终端位置信息类
 * @author YY_410
 * 2015-1-15
 * **/
public class TerminalLocationInfo {
	String tId;
	long tLatitude;
	long tLongitude;
	public String gettId() {
		return tId;
	}
	public void settId(String tId) {
		this.tId = tId;
	}
	public long gettLatitude() {
		return tLatitude;
	}
	public void settLatitude(long tLatitude) {
		this.tLatitude = tLatitude;
	}
	public long gettLongitude() {
		return tLongitude;
	}
	public void settLongitude(long tLongitude) {
		this.tLongitude = tLongitude;
	}
	
	
}
