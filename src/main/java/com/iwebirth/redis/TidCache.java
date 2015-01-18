package com.iwebirth.redis;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


public class TidCache {
	@Autowired
    CommonRedisClient commonRedisClient;
	
	Logger logger = Logger.getLogger(TidCache.class);
	/**
	 * @param tId terminal_id
	 * **/
	public boolean setTid(String tId){
		String key = tId;
		String value = tId;
		if(!commonRedisClient.exists(key)){
			commonRedisClient.set(key, value);
			return true;
		}else{
			return false;
		}
	}
	
	public String getTid(String tid){
		String key = tid;
		return commonRedisClient.get(key);
	}
	
	
	public void delTid(String tid){
		String key = tid;
		commonRedisClient.delete(key);
	}
}
