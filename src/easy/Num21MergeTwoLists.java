package easy;

import entity.ListNode;

/**
 * 21. 合并两个有序链表
 * https://leetcode.cn/problems/merge-two-sorted-lists/?favorite=2cktkvj
 */
public class Num21MergeTwoLists {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode();
        ListNode list3 = dummy;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                list3.next = new ListNode(list1.val);
                list1 = list1.next;
            } else {
                list3.next = new ListNode(list2.val);
                list2 = list2.next;
            }
            list3 = list3.next;
        }
        list3.next = list1 != null ? list1 : list2;
        return dummy.next;
    }
}
