package medium;

/**
 * https://leetcode.cn/problems/perfect-squares
 * 279. 完全平方数
 */
public class Problem_0279_NumSquares {

    public int numSquares(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;

        for (int i = 1; i <= n; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j]);
            }

            dp[i]++;
        }

        return dp[n];
    }
}
