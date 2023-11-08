package medium;

import java.util.HashSet;
import java.util.Set;

/**
 * 128. 最长连续序列
 * https://leetcode.cn/problems/longest-consecutive-sequence
 */
public class Problem_0128_LongestConsecutive {

    public int longestConsecutive(int[] nums) {
        // 记录所有元素并去重，去重后并不影响结果
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int longestCount = 0;
        for (int num : nums) {
            // 最主要就是这一句，保证能进去循环的一定是连续序列的开头元素，即最小的元素
            // 为了避免同一个连续序列中的很多元素都去遍历，将时间复杂度变为了O(n)
            if (!set.contains(num - 1)) {
                int count = 1;
                while (set.contains(num + 1)) {
                    count++;
                    num++;
                }

                longestCount = Math.max(longestCount, count);
            }
        }

        return longestCount;
    }
}
