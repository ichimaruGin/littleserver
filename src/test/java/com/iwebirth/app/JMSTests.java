package com.iwebirth.app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.iwebirth.mom.jms.Animal;
import com.iwebirth.mom.jms.SimpleMessageSender;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
	"classpath:spring-base.xml",
	"classpath:spring-jms.xml"
})
public class JMSTests {
	
	@Autowired
	@Qualifier("messageSender")
	SimpleMessageSender messageSender;
	@Test
	public void testSendString(){
		System.out.println("test send");
		messageSender.sendStringMessage("Hello,ActiveMQ");
	}
	@Test
	public void testSendObject(){
		Animal a = new Animal(100, 100);
		System.out.println("test send");
		messageSender.sendObjectMessage(a);
	}
}
