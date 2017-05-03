package com.demo.my.pic.listener;

import java.util.Date;

import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

public class MinaServerHandler implements IoHandler {

	@Override
	public void exceptionCaught(IoSession arg0, Throwable arg1)
			throws Exception {
		// TODO Auto-generated method stub
		arg1.printStackTrace();
	}

	@Override
	public void inputClosed(IoSession arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		String str = message.toString();  
        System.out.println("���ܵ�����Ϣ:"+str);  
        Date date = new Date();  
        session.write( date.toString() );  
        System.out.println("Message written...");  
	}

	@Override
	public void messageSent(IoSession arg0, Object arg1) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		System.out.println("IP:"+session.getRemoteAddress().toString()+"�Ͽ�����");
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		 System.out.println("IP:"+session.getRemoteAddress().toString());  		
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		System.out.println( "IDLE " + session.getIdleCount(status));  
	}

	@Override
	public void sessionOpened(IoSession arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
