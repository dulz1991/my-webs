package com.demo.my.pic.mina;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.prefixedstring.PrefixedStringCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

public class MinaClient {
	
	private IoConnector connector = null;
	private IoSession session = null;
	private ConnectFuture connectFuture = null;
	private String ip = "";
	private int port = 0;

	public MinaClient(String ip, int port) {
		this.ip=ip;
		this.port=port;
		initMinaClient();
	}
	
	public void initMinaClient() {
		connector = new NioSocketConnector();  
        connector.getFilterChain().addLast( "logger", new LoggingFilter() );  
        connector.getFilterChain().addLast( "codec", new ProtocolCodecFilter( new PrefixedStringCodecFactory(Charset.forName("UTF-8"))));  
        connector.setHandler(new MinaClientHander());  
        connectFuture = connector.connect(new InetSocketAddress(ip, port));  
        //等待建立连接  
        connectFuture.awaitUninterruptibly();  
	}
	
	/**
	 * 获取session
	 * @return
	 */
	public IoSession getIoSession() {
		if(session!=null){
			return session;
		} else {
			return connectFuture.getSession();
		}
	}
	
	/**
	 * 关闭
	 */
	public void closeIoSession() {
		if(session!=null){  
            if(session.isConnected()){  
                session.getCloseFuture().awaitUninterruptibly();  
            }  
            connector.dispose(true);  
        }  
	}
	
}
