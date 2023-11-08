package hard;

/**
 * 312. 戳气球
 * https://leetcode.cn/problems/burst-balloons
 *
 * 题解：https://leetcode.cn/problems/burst-balloons/solutions/337630/zhe-ge-cai-pu-zi-ji-zai-jia-ye-neng-zuo-guan-jian-/
 * 该题解比较易懂
 */
public class Problem_0312_BurstBalloons {

    public int maxCoins(int[] nums) {
        int n = nums.length;

        int[] temp = new int[n + 2];
        temp[0] = 1;
        temp[temp.length - 1] = 1;
        for (int i = 0; i < n; i++) {
            temp[i + 1] = nums[i];
        }
        nums = temp;

        int[][] dp = new int[n + 2][n + 2];

        // len 表示开区间长度
        for (int len = 3; len <= n + 2; len++) {
            // i 表示开区间的左端点
            for (int i = 0; i <= n + 2 - len; i++) {
                int res = 0;
                // j 表示开区间的右端点
                int j = len + i - 1;

                // 开始尝试开区间(i, j)中，每一个点作为最后戳破的气球，所能拿到的最大数量的金币
                // 然后取最大值，即开区间(i, j)所能拿到的最大数量金币
                for (int k = i + 1; k < j; k++) {
                    int left = dp[i][k];
                    int right = dp[k][j];
                    res = Math.max(res, left + nums[i] * nums[k] * nums[j] + right);
                }
                dp[i][j] = res;
            }
        }

        return dp[0][n + 1];
    }
}
