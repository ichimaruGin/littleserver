package com.iwebirth.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * spring工具类
 * @author YY_410
 * 2015-1-14
 * **/
public class SpringUtils {
	public static ApplicationContext spring;
	static {
		String[] confs = {
				"file:src/main/resources/spring-base.xml",
				"file:src/main/resources/spring-redis.xml",
				"file:src/main/resources/spring-hibernate.xml"};
		spring = new ClassPathXmlApplicationContext(confs);
	}
}
