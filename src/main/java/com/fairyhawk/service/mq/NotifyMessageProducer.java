package com.fairyhawk.service.mq;

import java.util.Map;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

/**
 * JMS用户变更消息生产者.
 * 
 * 使用jmsTemplate将用户变更消息分别发送到queue与topic.
 * 
 * @author fairyhawk
 */
public class NotifyMessageProducer {

	private JmsTemplate jmsTemplate;
	private Destination notifyQueue;
	private Destination notifyTopic;

	public void sendQueue(Map<String, Object> map) {
		sendMessage(map, notifyQueue);
	}

	public void sendTopic(final Map<String, Object> map) {
		sendMessage(map, notifyTopic);
	}

	/**
	 * 使用jmsTemplate最简便的封装convertAndSend()发送Map类型的消息.
	 *//*
	private void sendMessage(Map<String, Object> map, Destination destination) {
		jmsTemplate.convertAndSend(destination, map);
	}*/

	/**
	 * 使用jmsTemplate的send/MessageCreator()发送Map类型的消息并在Message中附加属性用于消息过滤.
	 */
	private void sendMessage(final Map<String, Object> map, Destination destination) {
		jmsTemplate.send(destination, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {

				MapMessage message = session.createMapMessage();
				message.setString("userName", map.get("userName").toString());
				message.setString("email", map.get("email").toString());

				message.setStringProperty("objectType", "user");

				return message;
			}
		});
	}
	
	
	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public void setNotifyQueue(Destination notifyQueue) {
		this.notifyQueue = notifyQueue;
	}

	public void setNotifyTopic(Destination nodifyTopic) {
		this.notifyTopic = nodifyTopic;
	}

	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}

	public Destination getNotifyQueue() {
		return notifyQueue;
	}

	public Destination getNotifyTopic() {
		return notifyTopic;
	}

}
