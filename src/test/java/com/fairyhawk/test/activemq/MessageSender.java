package com.fairyhawk.test.activemq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.core.support.JmsGatewaySupport;

public class MessageSender extends JmsGatewaySupport{
	public void senmsg(final String msg,final String cusId,final String appId) {
		this.getJmsTemplate().send(new MessageCreator() {
			private Message message;
			public Message createMessage(Session session) throws JMSException {
				message = session.createTextMessage(msg);
					message.setStringProperty("JMSXUserID", cusId); // 消息所属的用户编码
					message.setStringProperty("JMSXApp1ID", appId);//　　　　 消息所属的应用程序编码
				return message;
				} 
		}); 
	}
}
