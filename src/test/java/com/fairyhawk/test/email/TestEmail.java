package com.fairyhawk.test.email;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.fairyhawk.common.service.EmailService;
import com.fairyhawk.test.Threads;

/**
 * @ClassName  TestEmail
 * @package com.fairyhawk.test.email
 * @description
 * @author  liuqinggang
 * @Create Date: 2013-3-14 下午7:35:12
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/applicationContext.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class })
public class TestEmail {
	@Autowired
	private EmailService emailService;
	@Test
	public void testEmail(){
		try {
			emailService.sendMail("testopenmail@163.com", "testopenmail@163.com","明天开会早点到公司", "明天开会早点到公司准备下");
			Threads.sleep(10000);
			System.out.println("++ test ok");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public EmailService getEmailService() {
		return emailService;
	}
	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}
	
}
