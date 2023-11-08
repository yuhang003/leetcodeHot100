package medium;


/**
 * 416. 分割等和子集
 * https://leetcode.cn/problems/partition-equal-subset-sum
 *
 * 01背包 一维数组：物品在外，容量在内，倒序
 *
 */
public class Problem_0416_CanPartition {

    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        if ((sum & 1) == 1) return false;

        int target = sum / 2;
        int[] dp = new int[target + 1];
        for (int i = 0; i < nums.length; i++) {
            for (int j = target; j >= nums[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]);
            }
        }

        return dp[target] == target;
    }
}
