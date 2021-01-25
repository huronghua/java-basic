/*
 * File Name:PrintABCUsingLockCondition is created on 2021/1/22 下午2:18 by Eric
 *
 * Copyright (c) 2021, xiaoyujiaoyu technology All Rights Reserved.
 *
 */
package com.github.java.juc.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Eric
 * @Description:使用lock、condition循环依次打印ABC字符5次
 * @date: 2021/1/22 下午2:18
 * @since JDK 1.8
 */
public class PrintABCUsingLockCondition {

    private int times;

    private int state;

    private Lock lock = new ReentrantLock();

    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();
    private Condition conditionC = lock.newCondition();

    public PrintABCUsingLockCondition(int times) {
        this.times = times;
    }

    private void PrintChar(String charactor, int taskNum, Condition current, Condition next) {
        // i是局部变量，属于线程私有
        lock.lock();
        try {
            int i = 0;
            while (i < times) {
                if (state % 3 != taskNum) {
                    current.await();
                }
                state++;
                i++;
                System.out.println(Thread.currentThread().getName() + charactor);
                next.signal();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        PrintABCUsingLockCondition printABCUsingLockCondition = new PrintABCUsingLockCondition(5);

        new Thread(new Runnable() {
            @Override
            public void run() {
                printABCUsingLockCondition
                    .PrintChar("A", 0, printABCUsingLockCondition.conditionA, printABCUsingLockCondition.conditionB);
            }
        }, "A线程").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                printABCUsingLockCondition
                    .PrintChar("B", 1, printABCUsingLockCondition.conditionB, printABCUsingLockCondition.conditionC);
            }
        }, "B线程").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                printABCUsingLockCondition
                    .PrintChar("C", 2, printABCUsingLockCondition.conditionC, printABCUsingLockCondition.conditionA);
            }
        }, "C线程").start();
    }
}
