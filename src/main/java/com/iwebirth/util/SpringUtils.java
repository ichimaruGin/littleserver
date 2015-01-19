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
				"spring-base.xml",
				"spring-redis.xml",	
				"spring-jms.xml",
				"spring-hibernate.xml"};
		spring = new ClassPathXmlApplicationContext(confs);
	}
}
