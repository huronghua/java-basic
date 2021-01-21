/*
 * File Name:Trap_42 is created on 2021/1/21 下午2:02 by Eric
 *
 * Copyright (c) 2021, xiaoyujiaoyu technology All Rights Reserved.
 *
 */
package com.github.java.leetcode;

/**
 * @author Eric
 * @Description: 接雨水
 * @date: 2021/1/21 下午2:02
 * @since JDK 1.8
 */
public class Trap_42 {
    public int trap(int[] height) {
        int capacity = 0;
        int left = 0;
        int right = height.length - 1;
        int leftMax = 0;
        int rightMax = 0;
        while (left < right) {
            if (height[left] > height[right]) {
                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    capacity += (rightMax - height[right]);
                }
                right--;
            } else {
                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    capacity += (leftMax - height[left]);
                }
                left++;
            }
        }
        return capacity;
    }
}
