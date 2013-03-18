package com.fairyhawk.test.activemq;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;


public class TestReciver { 
	private static ApplicationContext ctx = null; 
	static{ 
		//ctx = new ClassPathXmlApplicationContext("classpath:config/spring/activemq/aContext_amq1.xml");
		ctx= new FileSystemXmlApplicationContext("classpath:config/spring/aContext_amq1.xml");
	}
	public static void sentTextMsg(){ 
		MessageReceiver messageReceiver = (MessageReceiver)ctx.getBean("messageReceiver"); 
		while(true){
			messageReceiver.receiverTextMsg(); 
		}
	}
	public static void main(String[] args){ 
		sentTextMsg(); 
	} 
}
