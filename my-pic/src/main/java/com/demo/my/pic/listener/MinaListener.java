package com.demo.my.pic.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import java.io.IOException;  
import java.net.InetSocketAddress;  
import java.nio.charset.Charset;  
  
import org.apache.mina.core.service.IoAcceptor;  
import org.apache.mina.core.session.IdleStatus;  
import org.apache.mina.filter.codec.ProtocolCodecFilter;  
import org.apache.mina.filter.codec.prefixedstring.PrefixedStringCodecFactory;  
import org.apache.mina.filter.logging.LoggingFilter;  
import org.apache.mina.transport.socket.nio.NioSocketAcceptor; 

public class MinaListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try {
			initMinaServer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void initMinaServer() throws IOException {
		IoAcceptor acceptor = new NioSocketAcceptor();  
        acceptor.getFilterChain().addLast("logger", new LoggingFilter());  
        acceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new PrefixedStringCodecFactory(Charset.forName("UTF-8"))));  
        acceptor.setHandler(new MinaServerHandler());  
        acceptor.getSessionConfig().setReadBufferSize(2048);  
        acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 60);  
        acceptor.bind(new InetSocketAddress(9999));  
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
	}
	 
}
