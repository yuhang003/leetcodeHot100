package medium;

import entity.ListNode;

/**
 * https://leetcode.cn/problems/remove-nth-node-from-end-of-list/?favorite=2cktkvj
 * 19. 删除链表的倒数第 N 个结点
 */
public class Problem_0019_RemoveNthFromEnd {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode slow = dummy, fast = dummy;

        while (n > 0) {
            fast = fast.next;
            n--;
        }

        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }

        slow.next = slow.next.next;

        return dummy.next;
    }
}
