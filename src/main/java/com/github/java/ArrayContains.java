package com.github.java;

import org.apache.commons.lang.ArrayUtils;

import java.util.Arrays;

/**
 * @author Eric-hu
 * @Description: 判断数组（注意不是list）内是否含有指定的元素
 * @create 2017-12-28 17:36
 * @Copyright: 2017 www.banmatrip.com All rights reserved.
 **/
public class ArrayContains {
    public static void main(String[] args) {
        /*先变为list再调用list的contains方法*/
        Arrays.asList().contains(new Object());

        /*直接使用Apache-common-lang包中的contains方法*/
        String[] array = {"id","name","sex"};
        boolean contains = ArrayUtils.contains(array,"sex");
    }
}