package com.iwebirth.redis;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TidCache {
	@Autowired
    CommonRedisClient commonRedisClient;
	
	Logger logger = Logger.getLogger(TidCache.class);
	
	/**
	 * @param tId terminal_id
	 * **/
	public boolean setTid(String tId){
		String key = "cache-"+tId;
		if(commonRedisClient.exists(key)){
			String oldValue = commonRedisClient.get(key);
			logger.info("tid已经存在于redis,key-value :" + key+"-"+oldValue);
		}else{
			logger.info("tid不存在于redis");
		}
		commonRedisClient.set(key, tId);
		return true;
	}
	
	public String getTid(String tid){
		String key = "cache-"+tid;
		return commonRedisClient.get(key);
	}
}
