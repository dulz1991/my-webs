package om.demo.my.user.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class ActiveMQListener implements ServletContextListener {
	
	/*private ConsumerThread consumerThread; */

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		/*String str = null;
		if (str == null && consumerThread == null) {
			consumerThread = new ConsumerThread();
			consumerThread.start(); // servlet 上下文初始化时启动 socket
		}*/
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		/*if (consumerThread != null && consumerThread.isInterrupted()) {
			consumerThread.interrupt();
		}*/
	}
	
}
