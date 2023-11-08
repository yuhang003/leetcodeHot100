package medium;

/**
 * 309. 最佳买卖股票时机含冷冻期
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-with-cooldown
 */
public class Problem_0309_MaxProfit {

    // 动态规划，数组实现
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) return 0;

        int N = prices.length;
        int[] buy = new int[N];
        int[] sell = new int[N];
        buy[0] = -prices[0];
        sell[0] = 0;

        buy[1] = Math.max(buy[0], -prices[1]);
        sell[1] = Math.max(sell[0], buy[0] + prices[1]);

        for (int i = 2; i < N; i++) {
            buy[i] = Math.max(buy[i - 1], sell[i - 2] - prices[i]);
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);
        }


        return sell[N - 1];
    }

    // 动态规划，滚动数组优化
    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length < 2) return 0;

        int bestBuy = Math.max(-prices[0], -prices[1]);
        int bestSell1 = Math.max(0, prices[1] - prices[0]);
        int bestSell2 = 0;

        for (int i = 1; i < prices.length; i++) {
            int tmp = bestSell1;

            bestSell1 = Math.max(bestSell1, bestBuy + prices[i]);
            bestBuy = Math.max(bestBuy, bestSell2 - prices[i]);

            bestSell2 = tmp;
        }

        return bestSell1;
    }



    // 123. 买卖股票的最佳时机 III
    // 没有冷冻期，最多完成两笔交易
    public int maxProfit3(int[] prices) {
        return maxProfit(2, prices);
    }

    // 188. 买卖股票的最佳时机 IV
    // 没有冷冻期，最多完成 K 笔交易
    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length < 2) return 0;

        int N = prices.length;
        // 如果交易次数大于时刻数的一半，说明交易次数没有限制
        // 如果将prices数组转化为折线图，折线图中上涨的次数最多为时刻数的一半，
        // 如果交易次数大于时刻数的一半，就相当于没有限制次数，直接转化为 买卖股票的最佳时机 II
        if (k >= N / 2) {
            return maxProfit4(prices);
        }

        int[][] dp = new int[N][k + 1];
        // dp[...][0] 和 dp[0][...] 值都是 0，所以不用初始化了

        for (int j = 1; j <= k; j++) {
            // 先初始化dp[1][j]
            int p1 = dp[0][j];
            int best = Math.max(dp[1][j - 1] - prices[1], dp[0][j - 1] - prices[0]);
            dp[1][j] = Math.max(p1, best + prices[1]);

            for (int i = 2; i < N; i++) {
                p1 = dp[i - 1][j];
                best = Math.max(best, dp[i][j - 1] - prices[i]);
                dp[i][j] = Math.max(p1, best + prices[i]);
            }
        }

        return dp[N - 1][k];
    }

    // 122. 买卖股票的最佳时机 II
    // 没有冷冻期，不限制交易次数
    public int maxProfit4(int[] prices) {
        if (prices == null || prices.length == 0) return 0;

        int ans = 0;
        for (int i = 1; i < prices.length; i++) {
            ans += Math.max(0, prices[i] - prices[i - 1]);
        }

        return ans;
    }
}
