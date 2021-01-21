/*
 * File Name:Model is created on 2020/12/29 上午10:43 by Eric
 *
 * Copyright (c) 2020, xiaoyujiaoyu technology All Rights Reserved.
 *
 */
package com.github.java.producer_consumer;

/**
 * @author Eric
 * @Description:
 * @date: 2020/12/29 上午10:43
 * @since JDK 1.8
 */
public interface Model {

    Runnable newConsumer();

    Runnable newProducer();
}
