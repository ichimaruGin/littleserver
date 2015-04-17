package com.iwebirth.interact.model;
/**
 * 终端与littlesever通信
 * 终端其他的一些信息
 * @author YY_410
 * 2015-1-15
 * **/
public class TerminalOtherInfo extends TerminalBase{
	
	public String iId;

	public String detail;

    public TerminalOtherInfo(String iId, String detail) {
        this.iId = iId;
        this.detail = detail;
    }
}
