package com.iwebirth.interact.model;
/**
 * 终端与littlesever通信
 * 终端运行故障类(from obd2)
 * @author YY_410
 * 2015-1-15
 * **/
public class TerminalError extends TerminalBase{

	public String tId;

    public String errorCode;  //P10003

	public TerminalError(String tId,String errorCode){
		this.tId = tId;
		this.errorCode = errorCode;
	}
	
}
