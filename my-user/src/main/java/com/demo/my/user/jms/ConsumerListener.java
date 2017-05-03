/*package com.demo.my.user.jms;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.demo.my.base.bean.SysCfg;
import com.demo.my.base.common.KeyConstant;
import com.demo.my.base.servicebean.BlogServiceBean;
import com.demo.my.base.servicebean.SysCfgServiceBean;

@Component
public class ConsumerListener implements ApplicationContextAware {
	
	//队列名gzframe.demo
    @Resource(name="demoQueueDestination")
    private Destination destination;
	@Resource(name="jmsTemplate")
    private JmsTemplate jmsTemplate;
	@Resource(name = "sysCfgServiceBean")
	private SysCfgServiceBean sysCfgService;
	@Resource(name = "blogServiceBean")
	private BlogServiceBean blogService;
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		new ConsumerTask().start();
	}
    
	class ConsumerTask extends Thread{
		public void run(){
			while (true) {
				TextMessage tm = (TextMessage) jmsTemplate.receive(destination);
				if(tm==null){
					continue;
				}
		    	ObjectMapper objectMapper = null;
		        try {
		        	String des = destination.toString();
		        	String msg = tm.getText();
		        	if (StringUtils.isBlank(msg)) {
						continue;
					}
		        	if(msg.equals("cmd1")){
		        		int count = blogService.getCountByParm(new HashMap<String, Object>(), KeyConstant.MAPPER_BLOG, KeyConstant.SQL_ID_MAP_COUNT);
		        		SysCfg sysCfg = sysCfgService.getOne();
		        		String countJsonStr = sysCfg.getCountJson();
		        		Map<String, Object> countMap = new HashMap<String, Object>();
		        		objectMapper = new ObjectMapper();  
		        		
		        		if(StringUtils.isBlank(countJsonStr)){
		        			countMap.put("blogCount", count);
		        			countJsonStr = objectMapper.writeValueAsString(countMap);
		        		} else {
		        			countMap = objectMapper.readValue(countJsonStr, Map.class);
		        			countMap.put("blogCount", count);
		        			countJsonStr = objectMapper.writeValueAsString(countMap);
		        		}
		        		sysCfg.setCountJson(countJsonStr);
		        		sysCfgService.update(sysCfg, KeyConstant.MAPPER_SYS_CFG);
		        	}
		        } catch (JMSException e) {
		            e.printStackTrace();
		        } catch (JsonParseException e) {  
		            e.printStackTrace();  
		        } catch (JsonMappingException e) {  
		            e.printStackTrace();  
		        } catch (IOException e) {  
		            e.printStackTrace();  
		        }  
			}
		}
 }
	
    *//**
     * 接收消息
     *//*
    public TextMessage receive(Destination destination) {
    	TextMessage tm = (TextMessage) jmsTemplate.receive(destination);
    	ObjectMapper objectMapper = null;
    	 
        try {
        	String des = destination.toString();
        	String msg = tm.getText();
        	if(msg.equals("cmd1")){
        		int count = blogService.getCountByParm(new HashMap<String, Object>(), KeyConstant.MAPPER_BLOG, KeyConstant.SQL_ID_MAP_COUNT);
        		SysCfg sysCfg = sysCfgService.getOne();
        		String countJsonStr = sysCfg.getCountJson();
        		Map<String, Object> countMap = new HashMap<String, Object>();
        		objectMapper = new ObjectMapper();  
        		
        		if(StringUtils.isBlank(countJsonStr)){
        			countMap.put("blogCount", count);
        			countJsonStr = objectMapper.writeValueAsString(countMap);
        		} else {
        			countMap = objectMapper.readValue(countJsonStr, Map.class);
        			countMap.put("blogCount", count);
        			countJsonStr = objectMapper.writeValueAsString(countMap);
        		}
        		sysCfg.setCountJson(countJsonStr);
        		sysCfgService.update(sysCfg, KeyConstant.MAPPER_SYS_CFG);
        	}
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {  
            e.printStackTrace();  
        } catch (JsonMappingException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return tm;
    }

}
*/