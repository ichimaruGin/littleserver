package com.iwebirth.mina.client;

/**
 * Created by YY_410 on 2015/4/14.
 */
public class Main {
    public static void main(String[] args){
        String tid = "TID" + (int)(10000000*Math.random());
        ClientConnector connector = new ClientConnector();
        connector.start(tid);
        System.out.println("客户端发起连接,TID="+tid);
    }
}
