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
			consumerThread.start(); // servlet �����ĳ�ʼ��ʱ���� socket
		}*/
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		/*if (consumerThread != null && consumerThread.isInterrupted()) {
			consumerThread.interrupt();
		}*/
	}
	
}
