package com.iwebirth.app;

import org.junit.Test;

import com.iwebirth.mina.test.ClientConnector;

public class MinaTests {
	
	@Test
	public void client(){
		ClientConnector connector = new ClientConnector();
		connector.start();
	}
}
