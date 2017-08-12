package com.szkingdom.consumer;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import sun.security.krb5.internal.crypto.Des;

import javax.jms.*;

/******************************************************************************
 * Copyright (C) 1998-至今, SHENZHEN KINGDOM Co., Ltd.
 * All Rights Reserved.
 * ===========================================================================
 * 模块名称：消息消费模块
 * 功能说明：
 * 作    者：zhouyou01
 * 创建日期：2017-08-05 13:54
 * 版 本 号：
 * 修改历史：
 * <p/>
 * 修改日期                  姓名                         内容
 * ---------------------------------------------------------------------------
 *******************************************************************************/
public class ActiveMQConsumer {
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;

    //默认的连接地址
    private static final String BROKER_URL = ActiveMQConnection.DEFAULT_BROKER_URL;

    public static void main(String[] args) {
        //1、创建连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKER_URL);
        Connection connection = null;
        Session session = null;
        try {
            //2、创建连接
            connection = connectionFactory.createConnection();
            //3、启动连接
            connection.start();
            //4、创建session，使用自动确认的方式
            session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
            //5、创建消息目的地，连接消息队列
            //5.1 p2p模式
            //Destination destination = session.createQueue("firstQueue");

            //5.2 pus/sub模式
            Destination destination = session.createTopic("firstTopic");
            //6、创建消费者
            MessageConsumer consumer = session.createConsumer(destination);

            System.out.println("消费者启动成功！");
            //7、接收消息
            while (true) {
                TextMessage textMessage = (TextMessage) consumer.receive(3000);
                if (textMessage!=null){
                    System.out.println("消费消息："+textMessage.getText());
                }
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
