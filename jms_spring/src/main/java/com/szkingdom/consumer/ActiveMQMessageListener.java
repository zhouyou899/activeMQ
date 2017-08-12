package com.szkingdom.consumer;

import org.apache.activemq.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.listener.SessionAwareMessageListener;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;

/******************************************************************************
 * Copyright (C) 1998-至今, SHENZHEN KINGDOM Co., Ltd.
 * All Rights Reserved.
 * ===========================================================================
 * 模块名称：
 * 功能说明：
 * 作    者：zhouyou01
 * 创建日期：2017-08-06 21:25
 * 版 本 号：
 * 修改历史：
 * <p/>
 * 修改日期                  姓名                         内容
 * ---------------------------------------------------------------------------
 *******************************************************************************/
public class ActiveMQMessageListener implements SessionAwareMessageListener<Message> {

    @Autowired
    private JmsTemplate jmsTemplate;

    public void onMessage(Message message, Session session) throws JMSException {
        String text = null;
        try {
            TextMessage textMessage = (TextMessage) message;
            if (textMessage!=null){
                text =   textMessage.getText();
                System.out.println("接收到消息："+text);
            }
        } catch (JMSException e) {
            e.printStackTrace();
            //可以利用jmsTemplate将处理出现异常的消息重新发回队列
            final String errorMsg = text;
            jmsTemplate.send(new MessageCreator() {
                public javax.jms.Message createMessage(Session session) throws JMSException {
                    return session.createTextMessage(errorMsg);
                }
            });
        }
    }
}
