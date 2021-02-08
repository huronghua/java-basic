/*
 * File Name:ReverseList_206 is created on 2021/1/28 上午10:03 by Eric
 *
 * Copyright (c) 2021, xiaoyujiaoyu technology All Rights Reserved.
 *
 */
package com.github.java.leetcode;

import com.github.java.leetcode.support.ListNode;

/**
 * @author Eric
 * @Description: 反转链表（双指针法）
 * @date: 2021/1/28 上午10:03
 * @since JDK 1.8
 */
public class ReverseList_206 {

    public ListNode reverseList(ListNode head) {
        //申请节点，pre和 cur，pre指向null
        ListNode pre = null;
        ListNode cur = head;
        ListNode tmp = null;
        while (cur != null) {
            //记录当前节点的下一个节点
            tmp = cur.next;
            //然后将当前节点指向pre
            cur.next = pre;
            //pre和cur节点都前进一位
            pre = cur;
            cur = tmp;
        }
        return pre;
    }

    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        a.setNext(b);
        b.setNext(c);
        c.setNext(null);
        ReverseList_206 reverseList_206 = new ReverseList_206();
        reverseList_206.reverseList(a);
    }
}
