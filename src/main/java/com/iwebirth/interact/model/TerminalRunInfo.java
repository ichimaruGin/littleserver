package com.iwebirth.interact.model;

/**
 * 终端与littlesever通信
 * 终端运行信息类(from obd2)
 * @author YY_410
 * 2015-1-15
 * **/
public class TerminalRunInfo {
	String tId;
	String speed;
	String rotate;
    String waterTemperature;

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

    public TerminalRunInfo(String tId, String rotateSpeed, String speed, String waterTemperature) {
        this.tId = tId;
        this.rotate = rotateSpeed;
        this.speed = speed;
        this.waterTemperature = waterTemperature;
    }
}
