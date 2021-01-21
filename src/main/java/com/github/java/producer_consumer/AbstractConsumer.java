/*
 * File Name:AbstractConsumer is created on 2020/12/29 上午10:40 by Eric
 *
 * Copyright (c) 2020, xiaoyujiaoyu technology All Rights Reserved.
 *
 */
package com.github.java.producer_consumer;

/**
 * @author Eric
 * @Description:
 * @date: 2020/12/29 上午10:40
 * @since JDK 1.8
 */
public abstract class AbstractConsumer implements Consumer, Runnable {
    @Override
    public void run() {
        while (true) {
            try {
                consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
