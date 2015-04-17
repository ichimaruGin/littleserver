package com.iwebirth.mina.client;

import com.iwebirth.util.ContactUtils;
import org.apache.mina.core.session.IoSession;

/**
 * Created by YY_410 on 2015/4/14.
 */
public class MCRuninfoRunnable implements Runnable {
    private String tid;
    private IoSession session;
    private long sleepMillis;
    public MCRuninfoRunnable(String tid, IoSession session, long sleepMillis){
        this.tid = tid;
        this.session = session;
        this.sleepMillis = sleepMillis;
    }
    @Override
    public void run() {
        while(true){
            try {
                if(!session.isConnected() && session.isClosing())
                    break;
                session.write(ContactUtils.createMCRuninfo(tid, (String.valueOf(100 * Math.random()+10)).substring(0, 2), (String.valueOf(2000 * Math.random()+100)).substring(0, 3), (String.valueOf(100 * Math.random()+10)).substring(0, 2)));
                Thread.sleep(sleepMillis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
