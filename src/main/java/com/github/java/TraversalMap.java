package com.github.java;

import java.util.*;

/**
 * @author Eric-hu
 * @Description: 遍历Map的三种方式
 * @create 2017-12-28 14:31
 * @Copyright: 2017 www.banmatrip.com All rights reserved.
 **/
public class TraversalMap {
    public static void main(String[] args) {

        Map<String,Object> map = new HashMap<>();

        /*第一种方式entrySet,遍历之前应该先进行判Map空*/
        for(Map.Entry<String,Object> entry:map.entrySet()){
            String key = entry.getKey();
            Object value = entry.getValue();
        }

        /*第二种方式keySet和values,但在遍历的同时通过key去Map中获取value会导致效率变慢,不推荐*/
        for(String key : map.keySet()){
            System.out.println(key);
        }
        for(Object value : map.values()){
            System.out.println(value);
        }

        /*第三种方式,iterator,该方法在遍历取得key和value的同时可以使用iterator的remove对entry进行删除操作*/
        Iterator<Map.Entry<String, Object>> entrys = map.entrySet().iterator();
        while(entrys.hasNext()){
            Map.Entry<String, Object> entry = entrys.next();
            String key = entry.getKey();
            Object value = entry.getValue();
            //iterator内部实现了一个光标，指向当前的位置，每次next就会指向下一个位置，一旦通过.iterator方法重新获取就会校正光标位置到第一个元素
            entrys.remove();
        }
    }
}