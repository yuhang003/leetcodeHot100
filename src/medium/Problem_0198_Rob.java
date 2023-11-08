package medium;

/**
 * 198. 打家劫舍
 * https://leetcode.cn/problems/house-robber
 *
 * 思路：偷一个房子的时候，判断是偷当前的房子 + 上上个房子，还是不偷当前房子，偷上一个房子
 * dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
 */
public class Problem_0198_Rob {

    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];

        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }

        return dp[n - 1];
    }

    // 内存优化
    public int rob2(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];

        int preP = nums[0];
        int pre = Math.max(nums[0], nums[1]);

        for (int i = 2; i < n; i++) {
            int temp = pre;
            pre = Math.max(pre, preP + nums[i]);
            preP = temp;
        }

        return pre;
    }
}
