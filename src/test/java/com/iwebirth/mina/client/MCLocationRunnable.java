package com.iwebirth.mina.client;

import com.iwebirth.util.ContactUtils;
import org.apache.mina.core.session.IoSession;

/**
 * Created by YY_410 on 2015/4/14.
 */
public class MCLocationRunnable implements Runnable {
    private String tid;
    private IoSession session;
    private long sleepMillis;
    public MCLocationRunnable(String tid, IoSession session, long sleepMillis){
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
                session.write(ContactUtils.createMCLocation(tid,String.valueOf(180*Math.random()).substring(0,10),String.valueOf(90*Math.random()).substring(0, 9)));
                Thread.sleep(sleepMillis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
