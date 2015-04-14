package com.iwebirth.interact.model;
/**
 * 终端与littlesever通信
 * 终端油量
 * @author YY_410
 * 2015-1-15
 * **/
public class TerminalOilInfo {
	String tId;
	boolean oilException;
	String exceptionDetail;
	
	public String gettId() {
		return tId;
	}
	public void settId(String tId) {
		this.tId = tId;
	}

    public boolean isOilException() {
        return oilException;
    }

    public void setOilException(boolean oilException) {
        this.oilException = oilException;
    }

    public String getExceptionDetail() {
        return exceptionDetail;
    }

    public void setExceptionDetail(String exceptionDetail) {
        this.exceptionDetail = exceptionDetail;
    }
}
