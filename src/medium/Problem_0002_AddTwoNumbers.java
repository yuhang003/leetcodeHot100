package medium;

import entity.ListNode;

/**
 * https://leetcode.cn/problems/add-two-numbers/?favorite=2cktkvj
 * 2. 两数相加
 */
public class Problem_0002_AddTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode();
        ListNode node = dummy;
        int num = 0;

        while (l1 != null || l2 != null) {
            int n1 = l1 != null ? l1.val : 0;
            int n2 = l2 != null ? l2.val : 0;

            int sum = n1 + n2 + num;
            if (sum >= 10) {
                node.next = new ListNode(sum % 10);
                num = sum / 10;
            } else {
                node.next = new ListNode(sum);
                num = 0;
            }

            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;
            node = node.next;
        }

        if (num != 0) {
            node.next = new ListNode(num);
        }

        return dummy.next;
    }

}
