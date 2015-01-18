package com.iwebirth.mom.jms;

import java.io.Serializable;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
/**
 * 2015-1-18
 * **/
public class MessageConvertForSys implements MessageConverter{
	
	public Object fromMessage(Message message) throws JMSException,
			MessageConversionException {
		// TODO Auto-generated method stub
		System.out.println("[fromMessage@MessageConvertForSys]");
		return null;
	}


	public Message toMessage(Object object, Session session) throws JMSException,
			MessageConversionException {
		// TODO Auto-generated method stub
		System.out.println("[toMessage@MessageConvertForSys]");  
		ObjectMessage obj = session.createObjectMessage((Serializable)object);
		System.out.println(obj.getClass());
        return obj;
	}

}
