package com.github.java.juc;

public class MyDoubleCheckSingleton {
    private static volatile MyDoubleCheckSingleton instance = null;

    private MyDoubleCheckSingleton() {
    }

    public static MyDoubleCheckSingleton getInstance(){
        if (instance == null) {
            synchronized (MyDoubleCheckSingleton.class) {
                if(instance == null){
                    return new MyDoubleCheckSingleton();
                }
            }
        }
        return instance;
    }
}
