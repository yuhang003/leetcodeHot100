package medium;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 253. 会议室 II
 *
 * 堆排序
 */
public class Problem_0253_MinMeetingRooms {

    public int minMeetingRooms(int[][] intervals) {
        // 按开始时间进行排序
        Arrays.sort(intervals, (a, b) -> {
            return a[0] - b[0];
        });

        // 默认小根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int ans = 0;
        for (int[] interval : intervals) {
            while (!heap.isEmpty() && interval[0] >= heap.peek()) {
                heap.poll();
            }
            heap.add(interval[1]);
            ans = Math.max(ans, heap.size());
        }

        return ans;
    }
}
