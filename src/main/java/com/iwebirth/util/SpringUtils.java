package com.iwebirth.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringUtils {
	public static ApplicationContext spring;
	static {
		spring = new ClassPathXmlApplicationContext("file:src/main/resources/spring-base.xml");
	}
}
