package com.github.java.leetcode.thread;

/**
 * 通过notify实现循环依次打印ABC字符5次
 */
public class PrintABCUsingWaitNotify {

    private int times; // 控制打印次数
    private int state;   // 当前状态值：保证三个线程之间交替打印
    private static final Object lock = new Object();


    public PrintABCUsingWaitNotify(int times) {
        this.times = times;
    }

    private void printLetter(String name, int taskNum) {
        // 加synchronized控制多线程之间的并发
        synchronized (lock){
            for (int i = 0; i < times; i++) {
                while (state % 3 != taskNum) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + name);
                state++;
                lock.notifyAll();
            }
        }
    }


    public static void main(String[] args) {

        PrintABCUsingWaitNotify loopThread = new PrintABCUsingWaitNotify(5);
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
