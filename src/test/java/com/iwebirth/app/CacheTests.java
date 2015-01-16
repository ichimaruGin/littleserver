package com.iwebirth.app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.iwebirth.redis.TidCache;
import com.iwebirth.util.SpringUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
	"file:src/main/resources/spring-base.xml",
	"file:src/main/resources/spring-redis.xml"
})
public class CacheTests {
	
	@Autowired
	TidCache tideCache;
	
	@Test
	public void testTidCache(){
		String tId = "9527";
		tideCache.setTid(tId);
		System.out.println(tId+"#"+tideCache.getTid(tId));
	}
}
