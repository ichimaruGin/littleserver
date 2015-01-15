package com.iwebirth.interact.model;

/**
 * 终端与littlesever通信
 * 终端运行信息类(from obd2)
 * @author YY_410
 * 2015-1-15
 * **/
public class TerminalRunInfo {
	String tId;
	int tSpeed;   
	int tRotateSpped;
	int tWaterTemperature;
	
	public String gettId() {
		return tId;
	}
	public void settId(String tId) {
		this.tId = tId;
	}
	public int gettSpeed() {
		return tSpeed;
	}
	public void settSpeed(int tSpeed) {
		this.tSpeed = tSpeed;
	}
	public int gettRotateSpped() {
		return tRotateSpped;
	}
	public void settRotateSpped(int tRotateSpped) {
		this.tRotateSpped = tRotateSpped;
	}
	public int gettWaterTemperature() {
		return tWaterTemperature;
	}
	public void settWaterTemperature(int tWaterTemperature) {
		this.tWaterTemperature = tWaterTemperature;
	}
	
	
}
