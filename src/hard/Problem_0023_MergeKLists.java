package hard;

import entity.ListNode;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 23. 合并 K 个升序链表
 * https://leetcode.cn/problems/merge-k-sorted-lists
 *
 * 方法1：使用分之思想合并，时间复杂度为O(kn×logk)，空间复杂度因为递归，所以是O(logk)
 * 方法2：使用优先级队列（小根堆）时间复杂度为O(kn×logk)，空间复杂度因为使用优先级队列，所以是O(k)
 */
public class Problem_0023_MergeKLists {
    // 使用分治思想两两合并链表
    public ListNode mergeKLists(ListNode[] lists) {
        return process(lists, 0, lists.length - 1);
    }

    // 分治合并
    public ListNode process(ListNode[] lists, int start, int end) {
        if (start == end) return lists[start];
        if (start > end) return null;

        int mid = start + ((end - start) >> 1);
        return mergeTwoList(process(lists, start, mid), process(lists, mid + 1, end));
    }

    // 合并两个链表
    public ListNode mergeTwoList(ListNode l1, ListNode l2) {
        ListNode head = new ListNode();
        ListNode cur = head;

        while (l1 != null && l2 != null) {
            if (l1.val >= l2.val) {
                cur.next = l2;
                l2 = l2.next;
            } else {
                cur.next = l1;
                l1 = l1.next;
            }

            cur = cur.next;
        }

        cur.next = l1 == null ? l2 : l1;
        return head.next;
    }

    // 使用优先级队列
    public ListNode mergeKLists2(ListNode[] lists) {
        Queue<Node> queue = new PriorityQueue<>();

        for (ListNode node : lists) {
            if (node != null) {
                queue.offer(new Node(node.val, node));
            }
        }

        ListNode ans = new ListNode();
        ListNode cur = ans;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            cur.next = node.p;
            cur = cur.next;

            ListNode next = node.p.next;
            if (next != null) {
                queue.offer(new Node(next.val, next));
            }
        }

        return ans.next;
    }

    static class Node implements Comparable<Node> {
        public int value;
        public ListNode p;

        public Node(int value, ListNode p) {
            this.value = value;
            this.p = p;
        }

        @Override
        public int compareTo(Node o) {
            return this.value - o.value;
        }
    }
}


