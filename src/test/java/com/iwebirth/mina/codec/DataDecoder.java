package com.iwebirth.mina.codec;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DataDecoder extends CumulativeProtocolDecoder{
	
	private static Logger log = Logger.getLogger(DataDecoder.class);
	private static final int FRAME_MIN_LENGTH = 1; //根据最小数据帧长度得到
	private Charset charset;
	public DataDecoder(Charset charset){
		this.charset = charset;
	}
	@Override
	protected boolean doDecode(IoSession session, IoBuffer in,
			ProtocolDecoderOutput out) throws Exception {
		// TODO Auto-generated method stub
		//in默认容量为2048
		String frame = in.getString(charset.newDecoder());
        out.write(frame);
		return true;
	}

	/**
	 * @return 去除所有空格号
	 * **/
	private String trimAll(String frame){
		return frame.replaceAll("\\s", "");
	}

	public static String replaceBlank(String str) {
	        String dest = "";
	        if (str!=null) {
	            Pattern p = Pattern.compile("\\s+|\n|\r|\t");
	            Matcher m = p.matcher(str);
	            dest = m.replaceAll("");
	        }
	    return dest;
	}

}

