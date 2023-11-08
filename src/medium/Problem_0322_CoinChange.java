package medium;

/**
 * 322. 零钱兑换
 * https://leetcode.cn/problems/coin-change
 *
 * 背包问题：
 * 01背包：二维数组：物品在外，容量在内，正序  （内外都可以）
 *         一维数组：物品在外，容量在内，倒序
 * 完全背包： 一维数组： 求组合数： 物品在外，容量在内，正序  (组合类转移方程：dp[j] += dp[j - nums[i]])
 *                     求排列数： 容量在外，物品在内，正序
 * 组合不强调元素之间的顺序，排列强调元素之间的顺序
 * 这一题，是完全背包，求最少硬币数，组合排列两种都可以，默认用求组合的方式，更好理解
 */
public class Problem_0322_CoinChange {

    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int amount = 11;
        System.out.println(coinChange(coins, amount));
    }

    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];

        for (int i = 1; i <= amount; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                if (dp[j - coins[i]] != Integer.MAX_VALUE) {
                    dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
                }
            }
        }

        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }
}
