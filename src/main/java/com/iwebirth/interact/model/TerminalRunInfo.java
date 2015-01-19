package com.iwebirth.interact.model;

/**
 * 终端与littlesever通信
 * 终端运行信息类(from obd2)
 * @author YY_410
 * 2015-1-15
 * **/
public class TerminalRunInfo {
	String tId;
	int speed;   
	int rotateSpeed;
	int waterTemperature;
	
	public String gettId() {
		return tId;
	}
	public void settId(String tId) {
		this.tId = tId;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getRotateSpped() {
		return rotateSpeed;
	}
	public void setRotateSpped(int rotateSpeed) {
		this.rotateSpeed = rotateSpeed;
	}
	public int getWaterTemperature() {
		return waterTemperature;
	}
	public void setWaterTemperature(int waterTemperature) {
		this.waterTemperature = waterTemperature;
	}
	public TerminalRunInfo(String tId,Integer speed,Integer rotateSpeed,Integer waterTemperatue){
		this.tId = tId;
		this.speed = speed;
		this.rotateSpeed = rotateSpeed;
		this.waterTemperature = waterTemperatue;		
	}
	
	
}
