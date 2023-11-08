package hard;

/**
 * 32. 最长有效括号
 * https://leetcode.cn/problems/longest-valid-parentheses
 */
public class Problem_0032_LongestValidParentheses {

    public static void main(String[] args) {
//        String s = "()(())()(()";
        String s = "())";
        System.out.println(longestValidParentheses(s));
    }

    public static int longestValidParentheses(String s) {
        if (s == null || s.length() < 2) return 0;

        char[] chars = s.toCharArray();
        // dp[i]表示必须以 chars[i] 为结尾，最大的有效括号的长度
        // 所以如果chars[i] == '('，有效长度一定为0
        int[] dp = new int[chars.length];
        int ans = 0;
        int pre = 0;
        for (int i = 1; i < chars.length; i++) {
            // 左括号的时候，有效长度一定为0，不用管
            if (chars[i] == ')') {
                // 找到前一个有可能和我匹配的下标
                pre = i - dp[i - 1] - 1;
                // 如果有，且和我匹配上了
                if (pre >= 0 && chars[pre] == '(') {
                    // 最短长度为 2 + dp[i - 1]
                    int val = 2 + dp[i - 1];
                    if (pre - 1 >= 0) {
                        val += dp[pre - 1];
                    }
                    dp[i] = val;
                }
                ans = Math.max(ans, dp[i]);
            }
        }

        return ans;
    }
}
