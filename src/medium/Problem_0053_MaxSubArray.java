package medium;

/**
 * 53. 最大子数组和
 * https://leetcode.cn/problems/maximum-subarray/description/
 */
public class Problem_0053_MaxSubArray {

    public int maxSubArray(int[] nums) {
        // dp[i] 表示：以 nums[i] 结尾的连续子数组的最大和
        int[] dp = new int[nums.length];
        dp[0] = nums[0];

        int ans = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (dp[i - 1] > 0) {
                dp[i] = dp[i - 1] + nums[i];
            } else {
                dp[i] = nums[i];
            }

            // 更新最大值
            ans = Math.max(ans, dp[i]);
        }

        return ans;
    }

    // 因为dp[i]只和dp[i - 1]有关，所以可以优化空间
    public int maxSubArray2(int[] nums) {
        int pre = nums[0];
        int ans = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (pre > 0) {
                pre = pre + nums[i];
            } else {
                pre = nums[i];
            }

            ans = Math.max(ans, pre);
        }

        return ans;
    }
}
