package com.iwebirth.interact.model;

/**
 * 终端与littlesever通信
 * 终端油量
 *
 * @author YY_410
 *         2015-1-15
 *         *
 */
public class TerminalOilInfo extends TerminalBase {

    public String tId;

    public boolean oilException;

    public String exceptionDetail;

    public TerminalOilInfo(String tId, boolean oilException, String exceptionDetail) {
        this.tId = tId;
        this.oilException = oilException;
        this.exceptionDetail = exceptionDetail;
    }
}
