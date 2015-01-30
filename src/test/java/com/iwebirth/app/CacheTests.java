package com.iwebirth.app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.iwebirth.redis.CommonRedisClient;
import com.iwebirth.redis.TidCache;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
	"file:src/main/resources/spring-base.xml",
	"file:src/main/resources/spring-redis.xml"
})
public class CacheTests {
	
	@Autowired
	TidCache tidCache;
	
	@Autowired
	CommonRedisClient commonRedisClient;
	
	@Test
	public void testTidCache(){
		String tId = "9527";
		tidCache.setTid(tId);
		System.out.println(tId+"#"+tidCache.getTid(tId));
	}
	@Test
	public void testSet(){
		String key = "b-4";String value="bb";
		System.out.println(commonRedisClient.exists(key));
		commonRedisClient.set(key, value);
	}
	@Test
	public void testGetByKey(){
		String key = "name";
		System.out.println(commonRedisClient.exists(key));
		System.out.println(commonRedisClient.get(key));
	}
	@Test
	public void testDeleteByKey(){
		String key = "9527";
		System.out.println(commonRedisClient.exists(key));
		System.out.println(commonRedisClient.get(key));
		commonRedisClient.delete(key);
	}
	
	@Test
	public void testAdd(){
		String key = "123";
		String value = "yy";
		System.out.println(commonRedisClient.add(key, value));
	}
//	清除缓存
//	@Test
//	public void testClearAllCache(){
//		System.out.println(commonRedisClient.deleteCurrentDB());
//	}
}
