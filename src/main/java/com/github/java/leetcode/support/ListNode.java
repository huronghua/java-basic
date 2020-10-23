package com.github.java.leetcode.support;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 链表节点
 * @Auther:Eric https://github.com/huronghua
 * @Date:2019-06-30 20 34
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListNode {
	int val;
	ListNode next;

	public ListNode(int x) {
		val = x;
	}
}
