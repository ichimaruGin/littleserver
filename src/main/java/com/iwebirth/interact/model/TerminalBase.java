package com.iwebirth.interact.model;

import com.iwebirth.util.TimeUtils;

/**
 * Created by YY_410 on 2015/4/17.
 */
public class TerminalBase {

    public long time = System.currentTimeMillis()/1000;

    public String format_time = TimeUtils.getFormatTime(System.currentTimeMillis());

}
