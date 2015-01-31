package com.iwebirth.app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring-base.xml",
        "classpath:spring-hibernate.xml"
})
public class AppTest {
	@Test
	public void app(){
		System.out.println("Hello");
	}
}
