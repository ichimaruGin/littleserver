package com.iwebirth.interact.model;
/**
 * 终端与littlesever通信
 * 终端报警
 * @author YY_410
 * 2015-1-15
 * **/
public class TerminalAlarm {
	
	String tId;
	String alarmDetail; //专门建一个alarm的detail库
	int alarmLevel;     //警报等级
	
	public String gettId() {
		return tId;
	}
	public void settId(String tId) {
		this.tId = tId;
	}
	public String getAlarmDetail() {
		return alarmDetail;
	}
	public void setAlarmDetail(String alarmDetail) {
		this.alarmDetail = alarmDetail;
	}
	public int getAlarmLevel() {
		return alarmLevel;
	}
	public void setAlarmLevel(int alarmLevel) {
		this.alarmLevel = alarmLevel;
	}
	
	
}
