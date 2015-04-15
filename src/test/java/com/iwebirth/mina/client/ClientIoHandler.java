package com.iwebirth.mina.client;

import com.iwebirth.util.ContactUtils;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

public class ClientIoHandler extends IoHandlerAdapter{
    private String tid;
    public ClientIoHandler(String tid){
        this.tid = tid;
    }
	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		// TODO Auto-generated method stub
		super.exceptionCaught(session, cause);
	}

	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("收到来自server的回复："+ message);
        String frame = (String)message;
        String cmd = ContactUtils.getFragmentByIndex(frame,1);
        if(ContactUtils.R_CONNECT.equalsIgnoreCase(cmd) || ContactUtils.R_RECONNECT.equalsIgnoreCase(cmd)){
            session.write(ContactUtils.createMCConnect(this.tid));
            Thread mcLocationThread = new Thread(new MCLocationRunnable(tid,session,60*1000l));
            mcLocationThread.start();
        }else if(ContactUtils.R_LOCATION.equalsIgnoreCase(cmd)){

        }
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		// TODO Auto-generated method stub
		super.messageSent(session, message);
        System.out.println(this.tid+" send "+ message);
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		super.sessionClosed(session);
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		super.sessionCreated(session);
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
		// TODO Auto-generated method stub
		super.sessionIdle(session, status);
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		// TODO Auto-generated method stub
		super.sessionOpened(session);
	}

}
