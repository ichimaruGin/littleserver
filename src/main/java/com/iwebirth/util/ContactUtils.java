package com.iwebirth.util;

import com.iwebirth.interact.model.*;
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
    public static final String R_RECONNECT = "RECONNECT";

    public static String FRAME_HEADER = "5A5A";
    public static String FRAME_TAILER = "5C5C";
    public static String FRAME_SPILT = "#";
    
    private static Logger logger = Logger.getLogger(ContactUtils.class);
	/**
	 * @param requestContent e.g. ContactUtils.REQUEST_LOCATION
	 * --->5A5A#tId#location#5C5C
	 * **/
	public static String createRequestFrame(int requestContent){
		StringBuilder sb = new StringBuilder();
		sb.append(FRAME_HEADER); 
		switch(requestContent){
			case REQUEST_LOCATION:	sb.append(prefix(R_LOCATION));break;
			case REQUEST_RUNINFO:	sb.append(prefix(R_RUNINFO));break;
			case REQUEST_ERROR:		sb.append(prefix(R_ERROR));break;
			case REQUEST_ALARM:		sb.append(prefix(R_ALARM));break;
			case REQUEST_OIL:		sb.append(prefix(R_OIL));break;
			case REQUEST_OTHERS:	sb.append(prefix(R_OTHERS ));break;
			default:sb.append(prefix("NULL"));logger.debug("@ContactUtils@createRequestFrame--->"+"发送了无效请求");break;
		}		
		sb.append(prefix(FRAME_TAILER));
		logger.debug(sb.toString());
		return  sb.toString(); 
	}

	public static String createConnectionFrame(){
		return "5A5A#CONNECT#5C5C";
	}
	public static String createReconnectionFrame(){
		return "5A5A#RECONNECT#5C5C";
	}	
	public static String createHelloFrame(String tid){
		return "5A5A#"+tid+"#HELLO#5C5C";
	}
	/**
	 * 构造 "#content"这种格式的帧片段
	 * **/
	private static String prefix(String content){
		return FRAME_SPILT+content;
	}
	
	/**
	 * 处理#分隔的数据片段
	 * **/
	public static String getfragByIndex(String frame, int index){
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

    //clinet mock frame data
    public static String createMCConnect(String tid){
        return new StringBuilder().append(FRAME_HEADER).append(prefix(tid)).append(prefix(R_CONNECT)).append(prefix(FRAME_TAILER)).toString();
    }

    public static String createMCLocation(String tid,String lng,String lat){
        return new StringBuilder().append(FRAME_HEADER).append(prefix(tid)).append(prefix(R_LOCATION)).append(prefix(lng)).
        append(prefix(lat)).append(prefix(FRAME_TAILER)).toString();
    }

    public static String createMCRuninfo(String tid,String speed,String rs,String wt){
        return new StringBuilder().append(FRAME_HEADER).append(prefix(tid)).append(prefix(R_RUNINFO)).append(prefix(speed)).
                append(prefix(rs)).append(prefix(wt)).append(prefix(FRAME_TAILER)).toString();
    }

    public static String createMCError(String tid,String errorCode){
        return new StringBuilder().append(FRAME_HEADER).append(prefix(tid)).append(prefix(R_ERROR)).append(prefix(errorCode)).
                append(prefix(FRAME_TAILER)).toString();
    }

    public static String createMCAlarm(String tid,String detail,String level){
        return new StringBuilder().append(FRAME_HEADER).append(prefix(tid)).append(prefix(R_ALARM)).append(prefix(detail)).
                append(prefix(level)).append(prefix(FRAME_TAILER)).toString();
    }

    public static String createMCOil(String tid,String exception,String exceptionDetail){
        return new StringBuilder().append(FRAME_HEADER).append(prefix(tid)).append(prefix(R_OIL)).append(prefix(exception)).
                append(prefix(exceptionDetail)).append(prefix(FRAME_TAILER)).toString();
    }

    //将数据帧转成对象
    public static Object convertFrameToObj(String frame){
        String cmd = getfragByIndex(frame,2);
        try{
            if(ContactUtils.R_LOCATION.endsWith(cmd)){
                return new TerminalLocationInfo(getfragByIndex(frame, 1), getfragByIndex(frame, 3), getfragByIndex(frame, 4));
            }else if(ContactUtils.R_RUNINFO.endsWith(cmd)){
                return new TerminalRunInfo(getfragByIndex(frame, 1),Integer.parseInt(getfragByIndex(frame, 3)),Integer.parseInt(getfragByIndex(frame, 5)),Integer.parseInt(getfragByIndex(frame, 4)));
            }else if(ContactUtils.R_ALARM.endsWith(cmd)){
                return new TerminalAlarm(getfragByIndex(frame, 1), getfragByIndex(frame, 3),Integer.parseInt(getfragByIndex(frame, 4)));
            }else if(ContactUtils.R_ERROR.endsWith(cmd)){
                return new TerminalError(getfragByIndex(frame, 1), getfragByIndex(frame, 3));
            }else if(ContactUtils.R_OIL.endsWith(cmd)){
                return new TerminalOilInfo(getfragByIndex(frame, 1),Boolean.parseBoolean(getfragByIndex(frame, 3)), getfragByIndex(frame, 4));
            }else if(ContactUtils.R_OTHERS.endsWith(cmd)){
                return new TerminalOtherInfo(getfragByIndex(frame, 1), getfragByIndex(frame, 3));
            }else{
                System.out.println("cmd not exists");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
