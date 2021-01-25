/*
 * File Name:LevelOrder_102 is created on 2021/1/25 上午9:45 by Eric
 *
 * Copyright (c) 2021, xiaoyujiaoyu technology All Rights Reserved.
 *
 */
package com.github.java.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.github.java.leetcode.support.TreeNode;

/**
 * @author Eric
 * @Description:二叉树的层序遍历
 * @date: 2021/1/25 上午9:45
 * @since JDK 1.8
 */
public class LevelOrder_102 {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        ArrayDeque<TreeNode> deque = new ArrayDeque<>();
        if (Objects.nonNull(root)) {
            deque.add(root);
            while (!deque.isEmpty()) {
                int levelSize = deque.size();
                // 先记录每层的节点数
                List<Integer> levelList = new ArrayList<>();
                // 一次循环poll出一层所有的节点并添加下层的所有节点
                for (int i = 0; i < levelSize; i++) {
                    TreeNode node = deque.poll();
                    if (Objects.nonNull(node.left)) {
                        deque.add(node.left);
                    }
                    if (Objects.nonNull(node.right)) {
                        deque.add(node.right);
                    }
                    levelList.add(node.val);
                }
                result.add(levelList);
            }
        }
        return result;
    }
}
