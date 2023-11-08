package medium;

import entity.ListNode;

/**
 * 148. 排序链表
 * https://leetcode.cn/problems/sort-list
 */
public class Problem_0148_SortList {

    public static void main(String[] args) {
//        ListNode head = new ListNode(4, new ListNode(2, new ListNode(1, new ListNode(3))));
        ListNode head = new ListNode(-1, new ListNode(5, new ListNode(3, new ListNode(4, new ListNode(-1)))));
        ListNode sortHead = sortList(head);
        System.out.println(sortHead);
    }

    public static ListNode sortList(ListNode head) {
        ListNode sortNode = head;
        ListNode cur = head;

        while (cur != null && cur.next != null) {
            cur = cur.next;
            if (cur.val < sortNode.val) {
                ListNode temp = head;
                ListNode pre = null;
                while (temp.val < cur.val) {
                    pre = temp;
                    temp = temp.next;
                }
                sortNode.next = cur.next;
                temp = cur;
                if (pre == null) {
                    temp.next = head;
                    head = temp;
                } else {
                    temp.next = pre.next;
                    pre.next = temp;
                }
                cur = sortNode;
            } else {
                sortNode = cur;
            }
        }

        return head;

    }
}
