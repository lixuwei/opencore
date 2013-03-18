package com.fairyhawk.test.activemq;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;


	public class TestSender { 
		private static ApplicationContext ctx = null; 
		static{ 
			//ctx = new ClassPathXmlApplicationContext("classpath*:config/spring/activemq/aContext_amq1.xml");
			ctx= new FileSystemXmlApplicationContext("classpath:config/spring/aContext_amq1.xml");
		}
		public static void sentTextMsg(){ 
			
			MessageSender messageSender = (MessageSender)ctx.getBean("messageSender"); 
			messageSender.senmsg("测试sender...111000","347","1001"); 
		}
		public static void main(String[] args){ 
			sentTextMsg(); 
			System.out.println("+++ sender ok ");
		} 
}