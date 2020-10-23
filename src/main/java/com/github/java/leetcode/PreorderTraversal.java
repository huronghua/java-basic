/*
 * File Name:PreorderTraversal is created on 2020/10/2215:58 by Eric
 *
 * Copyright (c) 2020, xiaoyujiaoyu technology All Rights Reserved.
 *
 */
package com.github.java.leetcode;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;

import com.github.java.leetcode.support.TreeNode;

/**
 * @author Eric
 * @Description:
 * @date: 2020/10/22 15:58
 * @since JDK 1.8
 */
public class PreorderTraversal {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    private static List<Integer> preorderTraversal(TreeNode root) {
        LinkedList<TreeNode> stack = new LinkedList<>();
        List<Integer> result = new LinkedList<>();

        if (root == null) {
            return result;
        }

        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode treeNode = stack.pollLast();
            result.add(treeNode.val);
            if (treeNode.right != null) {
                stack.add(treeNode.right);
            }
            if (treeNode.left != null) {
                stack.add(treeNode.left);
            }
        }
        return result;
    }
}
