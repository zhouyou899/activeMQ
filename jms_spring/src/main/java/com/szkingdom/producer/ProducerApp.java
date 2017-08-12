package com.szkingdom.producer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/******************************************************************************
 * Copyright (C) 1998-至今, SHENZHEN KINGDOM Co., Ltd.
 * All Rights Reserved.
 * ===========================================================================
 * 模块名称：消息发送模块
 * 功能说明：该案例是每启动一次就会发送一条消息到队列中
 * 作    者：zhouyou01
 * 创建日期：2017-08-06 16:59
 * 版 本 号：
 * 修改历史：
 * <p/>
 * 修改日期                  姓名                         内容
 * ---------------------------------------------------------------------------
 *******************************************************************************/
public class ProducerApp {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-activeMQ-producer.xml");
        ActiveMQProducer producer = (ActiveMQProducer)context.getBean("producer");
        System.out.println("生产者启动成功！");
        producer.sendMessage("spring-jms消息2");
    }
}
