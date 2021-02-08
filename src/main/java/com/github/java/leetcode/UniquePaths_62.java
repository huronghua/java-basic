/*
 * File Name:UniquePaths is created on 2021/2/7 下午4:38 by Eric
 *
 * Copyright (c) 2021, xiaoyujiaoyu technology All Rights Reserved.
 *
 */
package com.github.java.leetcode;

/**
 * @author Eric
 * @Description: 不同路径(每次只能往下或者往右一格)
 * @date: 2021/2/7 下午4:38
 * @since JDK 1.8
 */
public class UniquePaths_62 {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        // 初始化第一行
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        // 初始化第一列
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        // 遍历所有情况求取的结果
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        // 返回结果从0开始
        return dp[m-1][n-1];
    }

    public static void main(String[] args) {
        UniquePaths_62 uniquePaths_62 = new UniquePaths_62();
        System.out.println(uniquePaths_62.uniquePaths(4,7));
    }
}
