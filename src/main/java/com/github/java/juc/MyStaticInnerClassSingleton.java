package com.github.java.juc;

/**
 * 线程安全的单例模式
 */
public class MyStaticInnerClassSingleton {

    private MyStaticInnerClassSingleton() {
    }

    private static class SingletonBuilder {
        private static MyStaticInnerClassSingleton instance = new MyStaticInnerClassSingleton();
    }

    public static MyStaticInnerClassSingleton getInstance() {
        return SingletonBuilder.instance;
    }
}
