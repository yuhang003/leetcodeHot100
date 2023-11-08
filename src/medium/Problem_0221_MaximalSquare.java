package medium;

/**
 * 221. 最大正方形
 * https://leetcode.cn/problems/maximal-square
 */
public class Problem_0221_MaximalSquare {

    public int maximalSquare(char[][] matrix) {
        int m = matrix.length, n = matrix[0].length;

        int maxSide = 0;
        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
                    }
                    maxSide = Math.max(dp[i][j], maxSide);
                }
            }
        }

        return maxSide * maxSide;
    }

    /**
     * 1277. 统计全为 1 的正方形子矩阵
     * https://leetcode.cn/problems/count-square-submatrices-with-all-ones
     * @param matrix
     * @return
     */
    public int countSquares(int[][] matrix) {
        // 将原数组作为dp数组来用
        int m = matrix.length, n = matrix[0].length;

        int maxSide = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    if (i > 0 && j > 0) {
                        matrix[i][j] = Math.min(matrix[i - 1][j], Math.min(matrix[i][j - 1], matrix[i - 1][j - 1])) + 1;
                    }
                    maxSide += matrix[i][j];
                }
            }
        }

        return maxSide;
    }
}
