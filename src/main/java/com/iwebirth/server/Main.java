package com.iwebirth.server;

import com.iwebirth.util.SpringUtils;

public class Main {
	public static void main(String[] args){
		Server server = (Server)SpringUtils.spring.getBean(Server.class);
		server.start();
	}
}
