package com.iwebirth.mina.test;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.RuntimeIoException;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

public class ClientConnector {
	final long timeoutInMillis = 5*1000;
	final String HOSTNAME = "127.0.0.1";
	final int PORT = 9527;
	public void start(){
		NioSocketConnector connector = new NioSocketConnector();
		connector.setConnectTimeoutMillis(timeoutInMillis);
		
		connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("utf-8"))));
		connector.setHandler(new ClientIoHandler());
		
		IoSession session = null;
		for(;;){
			try{
				ConnectFuture future = connector.connect(new InetSocketAddress(HOSTNAME, PORT));
	            future.awaitUninterruptibly();
	            session = future.getSession();
	            session.write("hello,server\n");
	            break;
			}catch(RuntimeIoException e){
				e.printStackTrace();
			}
		}		
		// wait until the summation is done
	    session.getCloseFuture().awaitUninterruptibly();
	    connector.dispose();
	}
}
