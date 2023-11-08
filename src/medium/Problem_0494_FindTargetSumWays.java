package medium;

/**
 * 494. 目标和
 * https://leetcode.cn/problems/target-sum
 *
 * 01背包：一维数组：物品在外，容量在内，倒序
 * (组合类转移方程：dp[j] += dp[j - nums[i]])
 */
public class Problem_0494_FindTargetSumWays {

    /**
     * 动态规划
     * 01 背包
     */
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        target = Math.abs(target);
        if (target > sum || (target + sum) % 2 != 0) return 0;

        int bagSize = (target + sum) / 2;
        int[] dp = new int[bagSize + 1];
        dp[0] = 1;

        for (int i = 0; i < nums.length; i++) {
            for (int j = bagSize; j >= nums[i]; j--) {
                dp[j] += dp[j - nums[i]];
            }
        }

        return dp[bagSize];
    }

    /**
     * 回溯
     * 暴力递归
     */
    public int findTargetSumWays1(int[] nums, int target) {
        return process1(nums, target, 0);
    }

    public int process1(int[] nums, int rest, int index) {
        if (index == nums.length) {
            return rest == 0 ? 1 : 0;
        }

        return process1(nums, rest - nums[index], index + 1)
                + process1(nums, rest + nums[index], index + 1);
    }
}
