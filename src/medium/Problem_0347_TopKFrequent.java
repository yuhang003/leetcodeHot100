package medium;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 347. 前 K 个高频元素
 * https://leetcode.cn/problems/top-k-frequent-elements
 */
public class Problem_0347_TopKFrequent {

    // 采用小顶堆，时间复杂度：O(n*logK)
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (int num : nums) {
            counts.put(num, counts.getOrDefault(num, 0) + 1);
        }

        // 小顶堆
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> {
            return a[1] - b[1];
        });

        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            int key = entry.getKey(), value = entry.getValue();

            if (queue.size() == k) {
                if (queue.peek()[1] < value) {
                    queue.poll();
                    queue.offer(new int[]{key, value});
                }
            } else {
                queue.offer(new int[]{key, value});
            }
        }

        int[] ans = new int[k];
        int index = 0;
        while (!queue.isEmpty()) {
            ans[index++] = queue.poll()[0];
        }

        return ans;
    }

    // 采用快排，时间复杂度：O(n)
    public int[] topKFrequent2(int[] nums, int k) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (int num : nums) {
            counts.put(num, counts.getOrDefault(num, 0) + 1);
        }

        int i = counts.size();
        int[][] arr = new int[i][2];
        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            arr[--i][0] = entry.getKey();
            arr[i][1] = entry.getValue();
        }

        process(arr, 0, arr.length - 1, k);
        int[] ans = new int[k];
        for (i = 0; i < k; i++) {
            ans[i] = arr[i][0];
        }

        return ans;
    }

    public void process(int[][] arr, int L, int R, int K) {
        if (L == R) return;

        swap(arr, R, L + (int) (Math.random() * (R - L + 1)));
        int pivot = partition(arr, L, R);
        if (K == pivot + 1) return;
        else if (K < pivot + 1 ) {
            process(arr, L, pivot - 1, K);
        } else {
            process(arr, pivot + 1, R, K);
        }
    }

    // 将数组按照次数排序，左边都比基准点大，右边都比基准点小
    public int partition(int[][] arr, int L, int R) {
        int left = L - 1;
        int index = L;

        while (index < R) {
            if (arr[index][1] <= arr[R][1]) {
                index++;
            } else {
                swap(arr, ++left, index++);
            }
        }
        swap(arr, ++left, R);
        return left;
    }

    public void swap(int[][] arr, int i, int j) {
        int[] tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
