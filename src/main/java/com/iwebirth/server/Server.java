package com.iwebirth.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.util.ReferenceCountingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.iwebirth.redis.CommonRedisClient;
import com.iwebirth.server.codec.MyProtocolCodecFactory;
import com.iwebirth.util.SpringUtils;

public class Server {
	private final static Logger log = Logger.getLogger(Server.class);
	private final static int basePort = 9527;
	@Autowired
	ServerIoHandler serverIoHandler;
	@Autowired
	LegalListFilter legalListFilter;
	@Autowired
	CommonRedisClient commonRedisClient;
	public void start(){
		IoAcceptor ioAcceptor = new NioSocketAcceptor();
		//first filterchain  --- legal ip filter
		ioAcceptor.getFilterChain().addFirst("LegalListFilter", new ReferenceCountingFilter(legalListFilter));
		//second filterchain --- codec filter
		ioAcceptor.getFilterChain().addLast("codec",new ProtocolCodecFilter(new MyProtocolCodecFactory(Charset.forName("UTF-8"))));
		//业务逻辑处理类ServerIoHandler.class
		ioAcceptor.setHandler(serverIoHandler);
		//设置全局会话的属性(当然也可以在handler中单独修改，session.getConfig().setXXX)
		ioAcceptor.getSessionConfig().setReadBufferSize(2048);
		ioAcceptor.getSessionConfig().setReaderIdleTime(5*60);
		try {
			ioAcceptor.bind(new InetSocketAddress(basePort));
			log.info("server start @port "+basePort);
			commonRedisClient.deleteCurrentDB();//由于server断开,在重启时把原有的链接全部从缓存中移除
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("server启动错误，错误异常: "+e.getMessage());
			System.exit(0);
		}
	}	
}
