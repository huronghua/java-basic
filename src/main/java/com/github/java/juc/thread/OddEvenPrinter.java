/*
 * File Name:OddEvenPrinter is created on 2021/1/22 上午9:28 by Eric
 *
 * Copyright (c) 2021, xiaoyujiaoyu technology All Rights Reserved.
 *
 */
package com.github.java.juc.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Eric
 * @Description: 两个线程交替打印奇数和偶数
 * @date: 2021/1/22 上午9:28
 * @since JDK 1.8
 */
public class OddEvenPrinter {

    private volatile AtomicInteger state = new AtomicInteger(0);

    private static final Object lock = new Object();

    private void printOddEven(int taskNum) {
        synchronized (lock) {
            while (true) {
                try {
                    if (state.get() % 2 != taskNum) {
                        lock.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().toString() + state);
                state.getAndIncrement();
                lock.notifyAll();
            }
        }
    }

    public static void main(String[] args) {
        OddEvenPrinter oddEvenPrinter = new OddEvenPrinter();

        new Thread(new Runnable() {
            @Override
            public void run() {
                oddEvenPrinter.printOddEven(0);
            }
        }, "奇数线程").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                oddEvenPrinter.printOddEven(1);
            }
        }, "偶数线程").start();
    }

}
