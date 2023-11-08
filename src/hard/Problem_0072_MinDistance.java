package hard;

/**
 * 72. 编辑距离
 * 测试链接：https://leetcode.cn/problems/edit-distance
 */
public class Problem_0072_MinDistance {

    public int minDistance(String word1, String word2) {
        return minCost(word1, word2, 1, 1, 1);
    }

    // ic 插入的花费，dc 删除的花费，rc 替换的花费
    public int minCost(String word1, String word2, int ic, int dc, int rc) {
        if (word1 == null || word2 == null) return 0;

        char[] str1 = word1.toCharArray();
        char[] str2 = word2.toCharArray();
        int N = str1.length + 1;
        int M = str2.length + 1;
        // dp[i][j] 代表 word1从0到i，转换成word2从0到j所花费的最小代价
        int[][] dp = new int[N][M];
        for (int i = 1; i < N; i++) {
            dp[i][0] = i * dc;
        }
        for (int j = 1; j < M; j++) {
            dp[0][j] = j * ic;
        }

        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                if (str1[i - 1] == str2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i - 1][j - 1] + rc;
                }

                dp[i][j] = Math.min(dp[i][j], Math.min(dp[i][j - 1] + ic, dp[i - 1][j] + dc));
            }
        }

        return dp[N - 1][M - 1];
    }
}
