package com.iwebirth.mina.client;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import com.iwebirth.mina.codec.MyProtocolCodecFactory;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

public class ClientConnector {
	final long timeoutInMillis = 5*1000;
	final String HOSTNAME = "127.0.0.1";
	final int PORT = 9527;

	public void start(String tid){
		NioSocketConnector connector = new NioSocketConnector();
		connector.setConnectTimeoutMillis(timeoutInMillis);
		
		connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new MyProtocolCodecFactory(Charset.forName("utf-8"))));
		connector.setHandler(new ClientIoHandler(tid));

        ConnectFuture future = connector.connect(new InetSocketAddress(HOSTNAME, PORT));
//        IoSession session = null;
//        future.awaitUninterruptibly();
//        session = future.getSession();
//		for(;;){
//			try{
//	            session.write(ContactUtils.createConnectionFrame());
//	            break;
//			}catch(RuntimeIoException e){
//				e.printStackTrace();
//			}
//		}
//		// wait until the summation is done
//	    session.getCloseFuture().awaitUninterruptibly();
//	    connector.dispose();
	}
}
