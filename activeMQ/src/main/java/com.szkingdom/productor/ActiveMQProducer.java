package com.szkingdom.productor;

import org.apache.activemq.*;

import javax.jms.*;

/******************************************************************************
 * Copyright (C) 1998-至今, SHENZHEN KINGDOM Co., Ltd.
 * All Rights Reserved.
 * ===========================================================================
 * 模块名称：消息生产者
 * 功能说明：
 * 作    者：zhouyou01
 * 创建日期：2017-08-05 13:17
 * 版 本 号：
 * 修改历史：
 * <p/>
 * 修改日期                  姓名                         内容
 * ---------------------------------------------------------------------------
 *******************************************************************************/
public class ActiveMQProducer {
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;

    //默认的连接地址
    private static final String BROKER_URL = "tcp://localhost:61616";

    public static void main(String[] args) {
        //1、创建连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(USERNAME,PASSWORD,BROKER_URL);
        Connection connection = null;
        Session session = null;
        try {
            //2、创建连接
            connection = connectionFactory.createConnection();
            //3、启动连接
            connection.start();
            //4、创建session，使用自动确认的方式
            session = connection.createSession(Boolean.TRUE,Session.AUTO_ACKNOWLEDGE);
            //5、创建消息目的地。P2P消息模式中目的地是一个队列
            Destination destination = session.createQueue("firstQueue");
            //6、创建生产者
            MessageProducer producer =session.createProducer(destination);
            //7、发送消息
            for (int i=0;i<10;i++){
                TextMessage message = session.createTextMessage("发送消息到ActiveMQ"+i);
                producer.send(message);
                System.out.println(message.getText());
            }
            session.commit();
        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            try {
                session.close();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }

    }
}
