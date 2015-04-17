package com.iwebirth.app;

import com.iwebirth.interact.model.IntimeInfo;
import com.iwebirth.interact.model.TerminalLocationInfo;
import com.iwebirth.interact.model.TerminalRunInfo;
import com.iwebirth.redis.CommonRedisClient;
import com.iwebirth.redis.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
	"classpath:spring-base.xml",
	"classpath:spring-redis.xml"
})
public class CacheTests {

	@Autowired
	CommonRedisClient commonRedisClient;

    @Autowired
    RedisService redisService;

    @Test
    public void testHash(){
        String key = "people";
        System.out.println(commonRedisClient.hasKey(key)?"key is exists":"a new key");
        HashMap<String,String> value = new HashMap<String,String>();
        value.put("name","jack");
        value.put("名字","杰克");
        if(!commonRedisClient.hasKey(key))
            commonRedisClient.hmset("people",value);
        List fields = new ArrayList();
        fields.add("name");fields.add("名字");
        ArrayList values = (ArrayList) commonRedisClient.hmget("people",fields); //获得多个filed的value
        System.out.println(values);
        String singleValue = commonRedisClient.hget("people","name");//获得单个filed的value
        System.out.println(singleValue);
    }

    @Test
    public void testGetMap(){
        String key = "asd";
        System.out.println(commonRedisClient.hasKey(key)?"key is exists":"a new key");
        HashMap<String,String> value = new HashMap<String,String>();
        value.put("name","jack");
        value.put("名字","杰克");
        if(!commonRedisClient.hasKey(key))
            commonRedisClient.hmset(key,value);
        Map map = commonRedisClient.hgetMap(key);
        showMap(map);

    }

    @Test
    public void refreshCacheMap(){
        String tid = "tid001";
        IntimeInfo intime = new IntimeInfo("tid123",45,80,"40.3123","120.2134",700);
        redisService.updateCacheMap( tid, intime);
        TerminalLocationInfo location = new TerminalLocationInfo(tid,"1.2341235","1.2343125");
        redisService.updateCacheMap( tid, location);
        TerminalRunInfo runInfo = new TerminalRunInfo(tid,45,700,80);
        redisService.updateCacheMap( tid, runInfo);
    }

    public void showMap(Map map){
        for(Object key : map.keySet()){
            System.out.println(key + "---"+ map.get(key));
        }
    }

	@Test
	public void clearAllCache(){
		System.out.println(commonRedisClient.deleteCurrentDB());
	}

    @Test
    public void testDelete(){
        Set<String> keys = commonRedisClient.getKeys("T*");
        System.out.println(keys.size());
        for (String key : keys) {
            System.out.println(key);
        }
    }
}
