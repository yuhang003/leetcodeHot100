package medium;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 394. 字符串解码
 * https://leetcode.cn/problems/decode-string
 *
 * 类似的所有左右括号的问题，都可以用这个递归模版解决
 * 只不过大多数需要用 栈 来解决，这一题不需要，所以直接用StringBuilder了
 */
public class Problem_0394_DecodeString {

    public String decodeString(String s) {
        return process(s.toCharArray(), 0)[0];
    }

    public String[] process(char[] str, int i) {
        String[] ans = null;
        StringBuilder builder = new StringBuilder();
        int cur = 0;

        while (i < str.length && str[i] != ']') {
            if (str[i] >= '0' && str[i] <= '9') {
                cur = cur * 10 + str[i++] - '0';
            } else if (str[i] != '[') { // 字母
                builder.append(str[i++]);
            } else { // 左括号
                ans = process(str, i + 1);
                while (cur-- > 0) {
                    builder.append(ans[0]);
                }
                cur = 0;
                i = Integer.parseInt(ans[1]) + 1;
            }
        }

        return new String[] {builder.toString(), String.valueOf(i)};
    }
}
