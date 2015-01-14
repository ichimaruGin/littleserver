package com.iwebirth.server.codec;

import java.nio.charset.Charset;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

public class DataEncoder extends ProtocolEncoderAdapter{

	private Charset charset;
	public DataEncoder(Charset charset) {
		// TODO Auto-generated constructor stub
		this.charset = charset;
	}
	public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
		// 分配128 bytes ， 折算为64个字符
		IoBuffer buffer = IoBuffer.allocate(128).setAutoExpand(true);
		String msg= (String)message;
		buffer.putString(msg, charset.newEncoder());
		//指针回退到起点
		buffer.flip();
		out.write(buffer);
	}

}
