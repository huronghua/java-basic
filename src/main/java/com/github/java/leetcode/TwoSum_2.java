package com.github.java.leetcode;

import com.github.java.leetcode.support.ListNode;

/**
 * @Description:
 * @Auther:Eric https://github.com/huronghua
 * @Date:2019-06-30 10 40
 */
public class TwoSum_2 {

	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode pre = new ListNode(0);
		ListNode cur = pre;
		int carry = 0;
		while(l1 != null || l2 != null) {
			int x = l1 == null ? 0 : l1.getVal();
			int y = l2 == null ? 0 : l2.getVal();
			int sum = x + y + carry;

			carry = sum / 10;
			sum = sum % 10;
			cur.setNext(new ListNode(sum));

			cur = cur.getNext();
			if(l1 != null)
				l1 = l1.getNext();
			if(l2 != null)
				l2 = l2.getNext();
		}
		if(carry == 1) {
			cur.setNext(new ListNode(carry));
		}
		return pre.getNext();
	}

	public static void main(String[] args) {

	}
}
