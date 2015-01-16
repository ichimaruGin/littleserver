package com.iwebirth.server;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iwebirth.redis.CommonRedisClient;
import com.iwebirth.redis.TidCache;
import com.iwebirth.util.ContactUtils;

@Component
public class ServerIoHandler extends IoHandlerAdapter{
	static final Logger logger = Logger.getLogger(ServerIoHandler.class);
	public static Map<String,IoSession> sessionMap = new HashMap<String, IoSession>();
	@Autowired
	CommonRedisClient commonRedisClient;
	@Autowired
	TidCache tidCache;
	
	public CommonRedisClient getCommonRedisClient(){
		return this.commonRedisClient;
	}
	
	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		// TODO Auto-generated method stub
		super.exceptionCaught(session, cause);
	}

	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		// TODO Auto-generated method stub
		//super.messageReceived(session, message);
		String msg = (String)message;
		String tId = null;
		String cmd = null;
		try{
			tId = ContactUtils.getFragmentByIndex(msg, 1); //get terminal id
			cmd = ContactUtils.getFragmentByIndex(msg, 2); //get the kind of msg
		}catch(Exception e){
			e.printStackTrace();
		}
		if(ContactUtils.R_CONNECT.endsWith(cmd)){
			//connect cmd
			logger.info("connect cmd");
			if(sessionMap.containsKey(tId)){
				IoSession oldSession = (IoSession)sessionMap.get(tId);
				if(!oldSession.isClosing() || oldSession.isConnected())
					oldSession.close(true);				
			}
			sessionMap.put(tId, session);//here we put tid---iosession into a map; todo: put it into redis or db;
			session.write(ContactUtils.createHelloFrame()); //send Hello to terminal
			System.out.println("tidCache"+tidCache);
			tidCache.setTid(tId);//加入redis缓存
		}else{
			if(!sessionMap.containsKey(tId)){
				//only connect cmd will put tid into sessionMap, if send data_frame directly while no tid in sessionMap,
				//terminal need send connect cmd to littleserver again
				logger.info("reconnect cmd");
				session.write(ContactUtils.createReconnectionFrame());
			}else{
				if(ContactUtils.R_LOCATION.endsWith(cmd)){
					logger.info("location cmd");
				}else if(ContactUtils.R_RUNINFO.endsWith(cmd)){
					logger.info("runinfo cmd");
				}else if(ContactUtils.R_ERROR.endsWith(cmd)){
					logger.info("error cmd");
				}else if(ContactUtils.R_ALARM.endsWith(cmd)){
					logger.info("alarm cmd");
				}else if(ContactUtils.R_OIL.endsWith(cmd)){
					logger.info("oil cmd");
				}else if(ContactUtils.R_OTHERS.endsWith(cmd)){
					logger.info("others cmd");
				}else{
					logger.info("null cmd");
				}
			}
		}
		
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		// TODO Auto-generated method stub
		super.messageSent(session, message);
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		//super.sessionClosed(session);
		String tId = getTidBySessionAndRemoveSession(session);
		if(tId != null && tId.length() > 0){
			tidCache.delTid(tId);
		}
		scanSessionMap();
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		super.sessionCreated(session);
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
		// TODO Auto-generated method stub
		super.sessionIdle(session, status);
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		logger.info("connection created,from address="+session.getRemoteAddress()+" sessionId="+session.getId());
		session.write(ContactUtils.createConnectionFrame());  //send connect_frame 
	}
	/**
	 * 从sessionMap中移除session，返回该session对应的tid
	 * **/
	String getTidBySessionAndRemoveSession(IoSession session){
		Set<String> keys = sessionMap.keySet();
		String tId = null;
		for(String key : keys){
			if(sessionMap.get(key) == session){
				tId = key;
				sessionMap.remove(key);
				break;
			}
		}
		return tId;
	}
	
	/**
	 * 查看sessionMap
	 * **/
	void scanSessionMap(){
		Set<String> keys = sessionMap.keySet();
		int count = 0;
		for(String key : keys){
			count ++;
			System.out.println(count+":"+key);
		}
	}
}
