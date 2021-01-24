package com.github.java.leetcode.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 三个线程循环依次打印ABC 5次
 *
 * 变量i是属于每个线程一份的
 */
public class PrintABCUsingLock {
    private int times; // 控制打印次数
    private int state;   // 当前状态值：保证三个线程之间交替打印
    private Lock lock = new ReentrantLock();

    public PrintABCUsingLock(int times) {
        this.times = times;
    }

    private void printLetter(String name, int targetNum) {
        int i = 0;
        while (i < times) {
            lock.lock();
            if (state % 3 == targetNum) {
                state++;
                i++;
                System.out.println(Thread.currentThread().getName() + name);
            }
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        PrintABCUsingLock loopThread = new PrintABCUsingLock(5);
        new Thread(() -> {
            loopThread.printLetter("A", 0);
        }, "A线程：").start();

        new Thread(() -> {
            loopThread.printLetter("B", 1);
        }, "B线程：").start();

        new Thread(() -> {
            loopThread.printLetter("C", 2);
        }, "C线程：").start();
    }
}
