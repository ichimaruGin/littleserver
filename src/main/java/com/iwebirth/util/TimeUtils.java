package com.iwebirth.util;

import java.text.SimpleDateFormat;

public class TimeUtils {
	/**
	 * @param date Date
	 * **/
	public static String getFormatTime(java.util.Date date){
		String res = "";
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm:ss.SSS");
		res = sdf.format(date);
		return res;
	}
	/**
	 * @param timeInMillis System.currentTimeInMillis()
	 * **/
	public static String getFormatTime(long timeInMillis){
		String res = "";
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm:ss.SSS");
		res = sdf.format(timeInMillis);
		return res;
	}
}
