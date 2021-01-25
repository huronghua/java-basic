/*
 * File Name:PrintABCUsingLockSupport is created on 2021/1/22 下午4:30 by Eric
 *
 * Copyright (c) 2021, xiaoyujiaoyu technology All Rights Reserved.
 *
 */
package com.github.java.juc.thread;

import java.util.concurrent.locks.LockSupport;

/**
 * @author Eric
 * @Description: 使用locksupport循环依次打印AB字符5次
 * @date: 2021/1/22 下午4:30
 * @since JDK 1.8
 */
public class PrintABCUsingLockSupport {

    private static Thread threadA, threadB;

    public static void main(String[] args) {
        threadA = new Thread(()->{
            for(int i = 0; i< 5; i++){
                System.out.println(Thread.currentThread().getName() + "A");
                LockSupport.unpark(threadB);
                LockSupport.park();
            }
        }, "A线程");

        threadB = new Thread(()->{
            for(int i = 0; i< 5; i++){
                LockSupport.park();
                System.out.println(Thread.currentThread().getName() + "B");
                LockSupport.unpark(threadA);
            }
        }, "B线程");

        threadA.start();
        threadB.start();
    }
}
