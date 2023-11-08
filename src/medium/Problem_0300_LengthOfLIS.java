package medium;

/**
 * 300. 最长递增子序列
 * https://leetcode.cn/problems/longest-increasing-subsequence
 */
public class Problem_0300_LengthOfLIS {

    // 动态规划，时间复杂度 O(N^2)
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) return 0;

        int[] dp = new int[nums.length];
        int ans = 0;

        for (int i = 0; i < nums.length; i++) {
            // dp[i] 表示以 nums[i] 结尾的最长递增子序列的长度
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                // dp[i] = max{dp[j] + 1} (0 <= j < i, nums[j] < nums[i])
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            ans = Math.max(ans, dp[i]);
        }

        return ans;
    }


    // 二分法，时间复杂度 O(N * logN)
    public int lengthOfLIS2(int[] nums) {
        if (nums.length == 0) return 0;

        int[] dp = new int[nums.length];
        int ans = 0;

        for (int num : nums) {
            int l = 0;
            int r = ans;
            // 找到大于num的第一个数的位置
            while (l < r) {
                int mid = l + ((r - l) >> 1);
                if (dp[mid] < num) {
                    l = mid + 1;
                } else {
                    r = mid;
                }
            }
            dp[l] = num;
            if (l == ans) ans++;
        }

        return ans;
    }
}
