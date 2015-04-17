package com.iwebirth.interact.model;

/**
 * 终端与littlesever通信
 * 终端运行信息类(from obd2)
 * @author YY_410
 * 2015-1-15
 * **/
public class TerminalRunInfo extends TerminalBase {

	public String tId;

    public int speed;

    public int rotate;

    public int waterTemperature;

    public TerminalRunInfo(String tId, int speed, int waterTemperature, int rotate) {
        this.tId = tId;
        this.speed = speed;
        this.waterTemperature = waterTemperature;
        this.rotate = rotate;
    }
}
