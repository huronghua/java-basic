package com.github.java;

import com.google.gson.Gson;
import org.apache.commons.lang.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Eric-hu
 * @Description: 数组转List
 * @create 2017-12-28 13:25
 * @Copyright: 2017 www.banmatrip.com All rights reserved.
 **/
public class ArrayToArrayList {

    public static void main(String[] args) {
        Gson gson = new Gson();
        String[] strArray = {"1","2","3","4"};
        List<String> strList = new ArrayList<>();
        /*方式一、Arrays.asList的方式，结果是Array类型的内部静态List
        * 但是这种方式产生的List是固定大小的，只能对元素进行变更但不能进行添加或者删除操作*/
        strList = Arrays.asList(strArray);
        /**方式二、Collections.add将Array转换为普通的ArrayList类型/
         * 这种方式不会出现上面的那两种坑
         */
        Collections.addAll(strList,strArray);

        System.out.println(gson.toJson(strList));

        //strList.add("5");
        //strList.remove(0);
        strList.set(0,"5");

        strArray = new String[]{"5"};
    }
}