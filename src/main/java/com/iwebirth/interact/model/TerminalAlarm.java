package com.iwebirth.interact.model;
/**
 * 终端与littlesever通信
 * 终端报警
 * @author YY_410
 * 2015-1-15
 * **/
public class TerminalAlarm extends TerminalBase{
	
	public String tId;

	public String alarmDetail; //专门建一个alarm的detail库

	public int alarmLevel;     //警报等级

    public TerminalAlarm(String tId, String alarmDetail, int alarmLevel) {
        this.tId = tId;
        this.alarmDetail = alarmDetail;
        this.alarmLevel = alarmLevel;
    }
}
