package com.szkingdom.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/******************************************************************************
 * Copyright (C) 1998-至今, SHENZHEN KINGDOM Co., Ltd.
 * All Rights Reserved.
 * ===========================================================================
 * 模块名称：
 * 功能说明：
 * 作    者：zhouyou01
 * 创建日期：2017-08-06 16:54
 * 版 本 号：
 * 修改历史：
 * <p/>
 * 修改日期                  姓名                         内容
 * ---------------------------------------------------------------------------
 *******************************************************************************/
public class ActiveMQProducer {

    /**
     * 使用自动装配，不需要提供set方法，按类型匹配
     */
    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendMessage(final String message){
        jmsTemplate.send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(message);
            }
        });

        System.out.println("消息【"+message+"】已经发送");
    }
}
