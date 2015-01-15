package com.iwebirth.app;

import org.junit.Test;

import com.iwebirth.util.ContactUtils;

public class UtilsTests {
	/**
	 * 构造数据帧
	 * **/
	@Test
	public void contactUtilsCreateRequestFrameTest(){
		for(int i = 1;i<=7;i++){
			System.out.println(ContactUtils.createRequestFrame("tId",i));
		}
//		ContactUtils.createRequestFrame("tId",ContactUtils.REQUEST_ERROR);
//		System.out.println(requestFrame);
	}
}
