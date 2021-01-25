/*
 * File Name:PrintABCUsingSemaphore is created on 2021/1/22 下午3:46 by Eric
 *
 * Copyright (c) 2021, xiaoyujiaoyu technology All Rights Reserved.
 *
 */
package com.github.java.juc.thread;

import java.util.concurrent.Semaphore;

/**
 * @author Eric
 * @Description:2个线程使用semaphore循环依次打印AB字符3次
 * @date: 2021/1/22 下午3:46
 * @since JDK 1.8
 */
public class PrintABCUsingSemaphore {

    private int times;

    public PrintABCUsingSemaphore(int times) {
        this.times = times;
    }

    //移除state标记当前顺序,避免线程抢到锁了然后不应该自己执行所进行的空转
    //private int state;

    private Semaphore semaphoreA = new Semaphore(1);
    private Semaphore semaphoreB = new Semaphore(0);

    private void prinCharactor(String charactor, Semaphore current, Semaphore next) {

        int i = 0;
        while (i < times) {
            if (current.tryAcquire()) {
                System.out.println(Thread.currentThread().getName() + charactor);
                next.release();
                i++;
            }
        }
    }

    public static void main(String[] args) {

        PrintABCUsingSemaphore printABCUsingSemaphore = new PrintABCUsingSemaphore(5);

        new Thread(new Runnable() {
            @Override
            public void run() {
                printABCUsingSemaphore
                    .prinCharactor("A", printABCUsingSemaphore.semaphoreA, printABCUsingSemaphore.semaphoreB);
            }
        }, "A线程").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                printABCUsingSemaphore
                    .prinCharactor("B", printABCUsingSemaphore.semaphoreB, printABCUsingSemaphore.semaphoreA);
            }
        }, "B线程").start();

    }

}
