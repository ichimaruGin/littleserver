package com.iwebirth.app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.iwebirth.redis.CommonRedisClient;
import com.iwebirth.redis.TidCache;

import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
	"classpath:spring-base.xml",
	"classpath:spring-redis.xml"
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
		String key = "b-4";String value="中文";
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
		String key = "people";
		System.out.println(commonRedisClient.exists(key));
		commonRedisClient.delete(key);
	}
	
	@Test
	public void testAdd(){
		String key = "123";
		String value = "yy";
		System.out.println(commonRedisClient.add(key, value));
	}

    @Test
    public void testHash(){
        String key = "people";
        System.out.println(commonRedisClient.exists(key)?"key is exists":"a new key");
        HashMap<String,String> value = new HashMap<String,String>();
        value.put("name","jack");
        value.put("名字","杰克");
        if(!commonRedisClient.exists(key))
            commonRedisClient.hmset("people",value);
        List fields = new ArrayList();
        fields.add("name");fields.add("名字");
        ArrayList values = (ArrayList) commonRedisClient.hmget("people",fields); //获得多个filed的value
        System.out.println(values);
        String singleValue = commonRedisClient.hget("people","name");//获得单个filed的value
        System.out.println(singleValue);
    }
//	清除缓存
//	@Test
//	public void testClearAllCache(){
//		System.out.println(commonRedisClient.deleteCurrentDB());
//	}
}
