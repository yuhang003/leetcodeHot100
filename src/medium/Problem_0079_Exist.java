package medium;

/**
 * 79. 单词搜索
 * https://leetcode.cn/problems/word-search
 */
public class Problem_0079_Exist {

    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (process(board, i, j, word.toCharArray(), 0)) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean process(char[][] board, int i, int j, char[] word, int k) {
        if (k == word.length) return true;
        // 越界
        if (i < 0 || i == board.length || j < 0 || j == board[0].length) return false;
        if (board[i][j] != word[k]) return false;

        char temp = board[i][j];
        board[i][j] = 0;
        boolean ans = process(board, i - 1, j, word, k + 1)
                || process(board, i + 1, j, word, k + 1)
                || process(board, i, j - 1, word, k + 1)
                || process(board, i, j + 1, word, k + 1);
        board[i][j] = temp;

        return ans;
    }
}
