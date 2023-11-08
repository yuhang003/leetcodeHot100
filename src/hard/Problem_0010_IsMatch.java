package hard;

/**
 * 10. 正则表达式匹配
 * https://leetcode.cn/problems/regular-expression-matching
 *
 * 本题首先由暴力迭代的思路入手
 * 第二种方法使用缓存进行记忆化搜索优化
 * 第三种方法在方法2的基础上，使用观察位置优化的方式将 while循环优化掉
 *
 * 第四种方法和前三种方法的思路不同，是使用动态规划的思路进行求解
 */
public class Problem_0010_IsMatch {

    // 初始版本，直接将所有可能性考虑到，然后暴力递归
    public boolean isMatch(String s, String p) {
        return process(s.toCharArray(), p.toCharArray(), 0, 0);
    }

    // 字符串从 si开始到结尾，是否能匹配上正则表达式从 ei开始到结尾的部分
    public boolean process(char[] s, char[] e, int si, int ei) {
        if (ei == e.length) return si == s.length;

        // ei + 1位置的字符，不是*
        if (ei + 1 == e.length || e[ei + 1] != '*') {
            return si != s.length && (s[si] == e[ei] || e[ei] == '.') && process(s, e, si + 1, ei + 1);
        }

        // ei + 1位置的字符，是*
        // * 前面的字符e[ei] 得先能匹配上 s[si],
        // 如果不能的话，只能让e[ei]有0个，接着往下匹配，也就是后面的 process(s, e, si, ei + 2)
        while (si != s.length && (s[si] == e[ei] || e[ei] == '.')) {
            // 假设 * 前面的字符 e[ei] = 'a'，先模拟0个a，下次循环模拟1个a
            if (process(s, e, si, ei + 2)) {
                return true;
            }
            // 为了下次循环，模拟多个a
            si++;
        }

        return process(s, e, si, ei + 2);
    }

    // 方法2：使用记忆化搜索优化
    public boolean isMatch2(String s, String p) {
        // dp[i][j] i 代表 s字符串的下标， j 代表 p字符串的下标
        // dp[i][j] == 0， 说明该结果还没有计算过
        // dp[i][j] == -1， 说明该结果计算过，但是返回 false
        // dp[i][j] == 1， 说明该结果计算过，并且是true
        int[][] dp = new int[s.length() + 1][p.length() + 1];
        return process2(s.toCharArray(), p.toCharArray(), 0, 0, dp);
    }

    // 字符串从 si开始到结尾，是否能匹配上正则表达式从 ei开始到结尾的部分
    public boolean process2(char[] s, char[] e, int si, int ei, int[][] dp) {
        if (dp[si][ei] != 0) return dp[si][ei] == 1;

        if (ei == e.length) {
            dp[si][ei] = si == s.length ? 1 : -1;
            return si == s.length;
        }

        boolean ans = false;
        // ei + 1位置的字符，不是*
        if (ei + 1 == e.length || e[ei + 1] != '*') {
            ans = si != s.length && (s[si] == e[ei] || e[ei] == '.') && process2(s, e, si + 1, ei + 1, dp);
        } else {
            // ei + 1位置的字符，是*
            // * 前面的字符e[ei] 得先能匹配上 s[si],
            // 如果不能的话，只能让e[ei]有0个，接着往下匹配，也就是后面的 process(s, e, si, ei + 2)
            while (si != s.length && (s[si] == e[ei] || e[ei] == '.')) {
                // 假设 * 前面的字符 e[ei] = 'a'，先模拟0个a，下次循环模拟1个a
                if (process2(s, e, si, ei + 2, dp)) {
                    ans = true;
                    break;
                }
                // 为了下次循环，模拟多个a
                si++;
            }

            ans = ans || process2(s, e, si, ei + 2, dp);
        }
        dp[si][ei] = ans ? 1 : -1;
        return ans;
    }
}
