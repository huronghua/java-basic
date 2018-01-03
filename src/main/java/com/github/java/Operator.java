package com.github.java;

/**
 * @author Eric-hu
 * @Description: +=操作符的实质
 * @create 2017-12-28 11:09
 * @Copyright: 2017 www.banmatrip.com All rights reserved.
 **/
public class Operator {
    public static void main(String[] args) {
        int i = 5;
        long j = 8;

        System.out.println(i+=j);
        /*+=类似操作的实质是进行对应操作之后进行强转为等号左边的类型*/
        System.out.println(i = (int) (i+j));
    }
}