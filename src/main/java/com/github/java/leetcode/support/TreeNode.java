/*
 * File Name:TreeNode is created on 2020/10/2114:39 by Eric
 *
 * Copyright (c) 2020, xiaoyujiaoyu technology All Rights Reserved.
 *
 */
package com.github.java.leetcode.support;

import lombok.Builder;
import lombok.Data;

/**
 * @author Eric
 * @Description: 树节点
 * @date: 2020/10/21 14:39
 * @since JDK 1.8
 */
@Data
@Builder
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
