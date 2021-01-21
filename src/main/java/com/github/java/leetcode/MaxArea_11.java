/*
 * File Name:MaxArea_11 is created on 2021/1/20 下午5:25 by Eric
 *
 * Copyright (c) 2021, xiaoyujiaoyu technology All Rights Reserved.
 *
 */
package com.github.java.leetcode;

/**
 * @author Eric
 * @Description: 盛最多水的容器
 * @date: 2021/1/20 下午5:25
 * @since JDK 1.8
 */
public class MaxArea_11 {

    public int maxArea(int[] height){

        int area = 0;
        int i = 0;
        int j = height.length - 1;
        while (i<j){
            int temp = (j - i) * Math.min(height[i], height[j]);
            area = Math.max(temp, area);
            if (height[i] < height[j]) {
                i++;
            } else {
                j++;
            }
        }
        return area;
    }


    public static void main(String[] args) {

    }


}
