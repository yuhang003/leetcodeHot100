package medium;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/generate-parentheses/description/?favorite=2cktkvj
 * 22. 括号生成
 */
public class Problem_0022_GenerateParenthesis {
    List<String> ans = new ArrayList<>();
    StringBuilder builder = new StringBuilder();

    public List<String> generateParenthesis(int n) {
        backtrack(0, 0, n);
        return ans;
    }

    public void backtrack(int open, int close, int max) {
        if (builder.length() == max << 1) {
            ans.add(builder.toString());
            return;
        }

        if (open < max) {
            builder.append("(");
            backtrack(open + 1, close, max);
            builder.deleteCharAt(builder.length() - 1);
        }

        if (close < open) {
            builder.append(")");
            backtrack(open, close + 1, max);
            builder.deleteCharAt(builder.length() - 1);
        }
    }
}
