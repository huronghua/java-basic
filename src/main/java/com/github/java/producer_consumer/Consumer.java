/*
 * File Name:Consumer is created on 2020/12/29 上午10:36 by Eric
 *
 * Copyright (c) 2020, xiaoyujiaoyu technology All Rights Reserved.
 *
 */
package com.github.java.producer_consumer;

/**
 * @author Eric
 * @Description:
 * @date: 2020/12/29 上午10:36
 * @since JDK 1.8
 */
public interface Consumer {
    void consume() throws InterruptedException;
}
