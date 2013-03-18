package com.fairyhawk.test.activemq;

import java.util.HashMap;
import java.util.Map;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.fairyhawk.service.mq.NotifyMessageProducer;
import com.fairyhawk.test.Threads;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/applicationContext.xml" })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class })
public class TestActiveMQ {

	@Autowired
	private NotifyMessageProducer advancedNotifyMessageProducer;

	@Autowired
	private JmsTemplate advancedJmsTemplate;

	@Autowired
	private Destination advancedNotifyTopic;

	private static Logger logger = LoggerFactory.getLogger(TestActiveMQ.class);
	/**
	 *  tese  Topic message
	 */
	public void topicMessage() {
		logger.info("+++ ttt");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userName", "fairyhawk");
		map.put("email", "730**3@qq.com");

		advancedNotifyMessageProducer.sendTopic(map);
		Threads.sleep(1000);
	}
	
	@Test
	public void queueMessage() {
		Threads.sleep(1000);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userName", "liuqinggang");
		map.put("email", "7305@qq.com");

		advancedNotifyMessageProducer.sendQueue(map);
		Threads.sleep(1000);

	}

	public void topicMessageWithWrongType() {
		Threads.sleep(1000);

		advancedJmsTemplate.send(advancedNotifyTopic, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {

				MapMessage message = session.createMapMessage();
				message.setStringProperty("objectType", "role");
				return message;
			}
		});

		Threads.sleep(10000);
	}

	public NotifyMessageProducer getAdvancedNotifyMessageProducer() {
		return advancedNotifyMessageProducer;
	}

	public void setAdvancedNotifyMessageProducer(
			NotifyMessageProducer advancedNotifyMessageProducer) {
		this.advancedNotifyMessageProducer = advancedNotifyMessageProducer;
	}

	public JmsTemplate getAdvancedJmsTemplate() {
		return advancedJmsTemplate;
	}

	public void setAdvancedJmsTemplate(JmsTemplate advancedJmsTemplate) {
		this.advancedJmsTemplate = advancedJmsTemplate;
	}

	public Destination getAdvancedNotifyTopic() {
		return advancedNotifyTopic;
	}

	public void setAdvancedNotifyTopic(Destination advancedNotifyTopic) {
		this.advancedNotifyTopic = advancedNotifyTopic;
	}

}
