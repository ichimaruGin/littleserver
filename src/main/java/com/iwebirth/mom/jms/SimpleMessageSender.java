package com.iwebirth.mom.jms;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

/**
 * @author YY_410
 * 2015-1-18
 * **/
public class SimpleMessageSender {

	public SimpleMessageSender() {
		// TODO Auto-generated constructor stub
	}
	@Autowired
	public JmsTemplate jmsTemplate;
	@Autowired
	public ConnectionFactory cf;
	
	public void setJmsTemplate(JmsTemplate jmsTemplate){
		this.jmsTemplate = jmsTemplate;
	}
	/**
	 * send message
	 * */
	public void sendStringMessage(String msg){
//		jmsTemplate.send(new MessageCreator() {		
//			@Override
//			public Message createMessage(Session session) throws JMSException {
//				// TODO Auto-generated method stub
////				Animal animal = new Animal(10, 10);
////				Message m = session.createObjectMessage(animal);				
//				Message m = session.createTextMessage("it's a textMessage");
//				return m;  //发送出去后，会转为ActiveMQObjectMessage类，接收端接收到Message后，可以转型为ActiveMQObjectMessage来处理
//			}
//		});
		jmsTemplate.convertAndSend(msg);
		System.out.println("消息发送完成！");

	}

	public void sendObjectMessage(Object msg){
		jmsTemplate.convertAndSend(msg);
		System.out.println("消息发送完成！");
	}
}
