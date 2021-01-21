/*
 * File Name:BlockingQueueModel is created on 2020/12/29 上午10:48 by Eric
 *
 * Copyright (c) 2020, xiaoyujiaoyu technology All Rights Reserved.
 *
 */
package com.github.java.producer_consumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Eric
 * @Description:
 * @date: 2020/12/29 上午10:48
 * @since JDK 1.8
 */
public class BlockingQueueModel implements Model {
    private BlockingQueue<Task> queue;

    private AtomicInteger integer = new AtomicInteger(0);

    public BlockingQueueModel(Integer size) {
        this.queue = new LinkedBlockingQueue<>(size);
    }

    @Override
    public Runnable newConsumer() {
        return new AbstractConsumer() {
            @Override
            public void consume() throws InterruptedException {
                    Task task = queue.take();
                    System.out.println(task.getNo());
                }
        };
    }

    @Override
    public Runnable newProducer() {
        return new AbstractProducer() {
            @Override
            public void produce() throws InterruptedException {
                Task task = Task.builder().no(integer.getAndIncrement()).build();
                queue.put(task);
                System.out.println(task.getNo());
            }
        };
    }

    public static void main(String[] args) {
        BlockingQueueModel model = new BlockingQueueModel(3);
        for(int i = 0; i<2; i++){
            new Thread(model.newConsumer()).start();
        }
        for(int i = 0; i<2; i++){
            new Thread(model.newProducer()).start();
        }

    }
}
