/*
 * File Name:MinCostClimbingStairs is created on 2020/10/2111:03 by Eric
 *
 * Copyright (c) 2020, xiaoyujiaoyu technology All Rights Reserved.
 *
 */
package com.github.java.leetcode;

/**
 * @author Eric
 * @Description: 最小台阶花费台阶（动态规划）
 * @date: 2020/10/21 11:03
 * @since JDK 1.8
 */
public class MinCostClimbingStairs {

    public static int minCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length + 1];
        dp[0] = 0;
        dp[1] = 0;
        for (int i = 2; i <= cost.length; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        return dp[cost.length];
    }

    public static void main(String[] args) {
        System.out.println(minCostClimbingStairs(new int[]{1,1,0,0}));
    }
}
