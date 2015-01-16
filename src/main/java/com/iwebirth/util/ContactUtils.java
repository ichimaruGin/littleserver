package com.iwebirth.util;

import org.apache.log4j.Logger;

/**
 * terminal与littleserver数据交互工具类
 * littleserver向terminal请求数据的api
 * 按照协议构造数据帧
 * @author YY_410
 * 2015-1-15
 * **/
public class ContactUtils {
    public static final int REQUEST_LOCATION = 0x01;
    public static final int REQUEST_RUNINFO  = 0x02;
    public static final int REQUEST_ERROR = 0x03;
    public static final int REQUEST_ALARM = 0x04;
    public static final int REQUEST_OIL = 0x05;
    public static final int REQUEST_OTHERS = 0x06;
    public static final int REQUEST_CONNECT = 0x07;
    
    
    public static final String R_LOCATION = "LOCATION";
    public static final String R_RUNINFO = "RUNINFO";
    public static final String R_ERROR = "ERROR";
    public static final String R_ALARM= "ALARM";
    public static final String R_OIL = "OIL";
    public static final String R_OTHERS = "OTHERS";
    public static final String R_CONNECT = "CONNECT";
    
    public static String FRAME_HEADER = "5A5A";
    public static String FRAME_TAILER = "5C5C";
    public static String FRAME_SPILT = "#";
    
    private static Logger logger = Logger.getLogger(ContactUtils.class);
	/**
	 * @param tId terminalId
	 * @param requestContent e.g. ContactUtils.REQUEST_LOCATION
	 * --->5A5A#tId#location#5C5C
	 * **/
	public static String createRequestFrame(String tId,int requestContent){
		StringBuilder sb = new StringBuilder();
		sb.append(FRAME_HEADER); 
		switch(requestContent){
			case REQUEST_LOCATION:	sb.append(createFragment(R_LOCATION));break;
			case REQUEST_RUNINFO:	sb.append(createFragment(R_RUNINFO));break;
			case REQUEST_ERROR:		sb.append(createFragment(R_ERROR));break;
			case REQUEST_ALARM:		sb.append(createFragment(R_ALARM));break;
			case REQUEST_OIL:		sb.append(createFragment(R_OIL));break;
			case REQUEST_OTHERS:	sb.append(createFragment(R_OTHERS ));break;
			default:sb.append(createFragment("NULL"));logger.debug("@ContactUtils@createRequestFrame--->"+"发送了无效请求");break;
		}		
		sb.append(createFragment(FRAME_TAILER));
		logger.debug(sb.toString());
		return  sb.toString(); 
	}

	public static String createConnectionFrame(){
		return "5A5A#CONNECT#5C5C";
	}
	public static String createReconnectionFrame(){
		return "5A5A#RECONNECT#5C5C";
	}	
	public static String createHelloFrame(){
		return "5A5A#Tid#OK#5C5C";
	}
	/**
	 * 构造 "#content"这种格式的帧片段
	 * **/
	private static String createFragment(String content){
		return FRAME_SPILT+content;
	}
	
	/**
	 * 处理#分隔的数据片段
	 * **/
	public static String getFragmentByIndex(String frame , int index){
		String[] fragments;
		String fragment = "";
		try{
			fragments = frame.split(FRAME_SPILT);
			fragment  = fragments[index];
		}catch(Exception e){
			logger.warn(e.getMessage());
		}
		return fragment;
	}
}
