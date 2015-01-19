package com.iwebirth.interact.model;
/**
 * 终端与littlesever通信
 * 终端运行故障类(from obd2)
 * @author YY_410
 * 2015-1-15
 * **/
public class TerminalError {
	String tId;
	String errorCode;  //P10003
	public String gettId() {
		return tId;
	}
	public void settId(String tId) {
		this.tId = tId;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public TerminalError(String tId,String errorCode){
		this.tId = tId;
		this.errorCode = errorCode;
	}
	
}
