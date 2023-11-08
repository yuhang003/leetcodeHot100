package medium;

import java.util.HashMap;
import java.util.Map;

/**
 * 560. 和为 K 的子数组
 * https://leetcode.cn/problems/subarray-sum-equals-k
 *
 * 前缀和 + map记录次数
 * 统计以每一个元素结尾，和为 k 的连续子数组个数
 * 所有个数相加，就是结果
 */
public class Problem_0560_SubarraySum {

    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> preSumCount = new HashMap<>();
        preSumCount.put(0, 1);

        int sum = 0;
        int count = 0;
        for (int num : nums) {
            sum += num;

            count += preSumCount.getOrDefault(sum - k, 0);

            preSumCount.put(sum, preSumCount.getOrDefault(sum, 0) + 1);
        }

        return count;
    }
}
