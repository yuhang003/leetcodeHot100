package medium;

import entity.ListNode;

/**
 * 142. 环形链表 II
 * https://leetcode.cn/problems/linked-list-cycle-ii
 */
public class Problem_0142_DetectCycle {

    public ListNode detectCycle(ListNode head) {
        // 定义快慢指针，初始化为头节点
        ListNode fast = head, slow = head;

        // 用一个循环来移动快慢指针
        while (fast != null && fast.next != null) {
            // 快指针每次移动两个节点
            fast = fast.next.next;
            // 慢指针每次移动一个节点
            slow = slow.next;

            // 如果快慢指针相遇了
            if (fast == slow) {
                // 将慢指针指回头结点
                slow = head;

                // 快慢指针开始一步步移动，直到相遇
                while (fast != slow) {
                    fast = fast.next;
                    slow = slow.next;
                }

                // 返回相遇的节点，即为入环点
                return fast;
            }
        }

        // 能走到这一步，一定是没有环
        return null;
    }
}
