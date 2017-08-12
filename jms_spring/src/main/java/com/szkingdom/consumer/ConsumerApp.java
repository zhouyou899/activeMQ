package com.szkingdom.consumer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/******************************************************************************
 * Copyright (C) 1998-至今, SHENZHEN KINGDOM Co., Ltd.
 * All Rights Reserved.
 * ===========================================================================
 * 模块名称：消费者模块
 * 功能说明：由消息监听接收消息，只需要启动即可接收消息
 * 作    者：zhouyou01
 * 创建日期：2017-08-06 21:30
 * 版 本 号：
 * 修改历史：
 * <p/>
 * 修改日期                  姓名                         内容
 * ---------------------------------------------------------------------------
 *******************************************************************************/
public class ConsumerApp {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-activeMQ-consumer.xml");
        System.out.println("消费者启动成功！");
    }
}
