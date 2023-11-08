package hard;

import java.util.*;

/**
 * 239. 滑动窗口最大值
 * https://leetcode.cn/problems/sliding-window-maximum
 */
public class Problem_0239_MaxSlidingWindow {

    public int[] maxSlidingWindow(int[] nums, int k) {
        MyQueue queue = new MyQueue();
        int[] ans = new int[nums.length - k + 1];

        for (int i = 0; i < k; i++) {
            queue.add(nums[i]);
        }
        int index = 0;
        ans[index++] = queue.getMax();

        for (int i = k; i < nums.length; i++) {
            queue.remove(nums[i - k]);
            queue.add(nums[i]);
            ans[index++] = queue.getMax();
        }

        return ans;
    }

    // 自定义一个队列，队列中的元素按从大到小排列
    // 如果加进来的值大于后面的值，就将后面的值移除，最终仍旧保持一个从大到小有序的排列
    // 移除元素的时候，如果移除的元素正好等于最前面的元素（即最大值），那么就将最大值移除，
    // 如果不等于，那就不用动，因为最前面的元素仍是最大值

    // 这样维护队列，就能保证，队列中的最前面元素一定就是最大值
    class MyQueue {
        private Deque<Integer> queue;

        public MyQueue() {
            queue = new LinkedList<>();
        }

        public void add(int value) {
            while (!queue.isEmpty() && queue.getLast() < value) {
                queue.removeLast();
            }
            queue.addLast(value);
        }

        public void remove(int value) {
            if (!queue.isEmpty() && queue.getFirst() == value) {
                queue.removeFirst();
            }
        }

        public int getMax() {
            return queue.isEmpty() ? 0 : queue.getFirst();
        }
    }
}
