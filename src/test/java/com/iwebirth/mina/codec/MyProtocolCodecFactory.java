package com.iwebirth.mina.codec;

import org.apache.log4j.Logger;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

import java.nio.charset.Charset;

/**
 * @author YY_410
 * 协议编解码工厂，采用utf-8编码
 * **/
public class MyProtocolCodecFactory implements ProtocolCodecFactory {

	private Logger logger = Logger.getLogger(MyProtocolCodecFactory.class);
	private DataDecoder dataDecoder = null;
	private DataEncoder dataEncoder = null;
	public MyProtocolCodecFactory(Charset charset) {
		// TODO Auto-generated constructor stub
		dataDecoder = new DataDecoder(charset);
		dataEncoder = new DataEncoder(charset);
	}


	public ProtocolDecoder getDecoder(
			org.apache.mina.core.session.IoSession arg0) throws Exception {
		// TODO Auto-generated method stub
		if(dataDecoder == null)
			logger.warn("DataDecoder is null"+new java.sql.Timestamp(System.currentTimeMillis()));
		return dataDecoder;
		
	}

	public ProtocolEncoder getEncoder(
			org.apache.mina.core.session.IoSession arg0) throws Exception {
		// TODO Auto-generated method stub
		if(dataEncoder == null)
			logger.warn("DataEncoder is null"+new java.sql.Timestamp(System.currentTimeMillis()));
		return dataEncoder;
	}

}
