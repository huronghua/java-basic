/*
 * File Name:PreorderTraversal is created on 2020/10/2215:58 by Eric
 *
 * Copyright (c) 2020, xiaoyujiaoyu technology All Rights Reserved.
 *
 */
package com.github.java.leetcode;

import java.util.LinkedList;
import java.util.List;

import com.github.java.leetcode.support.TreeNode;

/**
 * @author Eric
 * @Description: 二叉树的前序遍历(迭代解法)
 * @date: 2020/10/22 15:58
 * @since JDK 1.8
 */
public class PreorderTraversal_144 {

    /**
     * <pre> 前序遍历 -> 根节点->左节点->右节点
     * 初始化栈，并将根节点入栈；
     * 当栈不为空时：
     * 弹出栈顶元素 node，并将值添加到结果中；
     * 如果 node 的右子树非空，将右子树入栈；
     * 如果 node 的左子树非空，将左子树入栈；
     * </pre>
     * @param root
     * @return
     */
    private static List<Integer> preorderTraversal(TreeNode root) {

        // 使用栈实现先入后出,前序遍历先压入右节点
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
