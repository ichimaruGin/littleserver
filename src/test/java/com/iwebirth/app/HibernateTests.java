package com.iwebirth.app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.iwebirth.db.dao.InsertDao;
import com.iwebirth.interact.model.TerminalError;
import com.iwebirth.interact.model.TerminalRunInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
	"classpath:spring-base.xml",
    "classpath:spring-hibernate.xml"
})
public class HibernateTests {
	@Autowired
	InsertDao insertService;
	
	@Test
	public void test(){
		System.out.println("Hibernate Initialize");
	}
	/**
	 * 插入运行信息
	 * **/
	@Test
	public void insertRunInfo(){
		TerminalRunInfo model = new TerminalRunInfo("EV05710001", 30, 2000, 85);
		insertService.insertRunInfo(model);
	}
	/**
	 * 插入obdError
	 * **/
	@Test
	public void insertError(){
		TerminalError tError = new TerminalError("EV05710001", "P0001");
		insertService.insertErrorModel(tError);
	}
	@Test
	public void initialErrorRef(){
		insertService.initialErrorReferrence();
	}
}
