package hard;

import java.util.HashMap;
import java.util.Map;

/**
 * 76. 最小覆盖子串
 * https://leetcode.cn/problems/minimum-window-substring
 */
public class Problem_0076_MinWindow {

    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) return "";

        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();
        int[] map = new int[256];
        // 构建好还需要补齐的字符数量
        for (char c : str2) {
            map[c]++;
        }

        int L = 0, R = 0;
        int minLen = Integer.MAX_VALUE;
        // 待补齐的字符
        int diffCount = str2.length;
        // 最小子串的起始下标
        int minStart = 0;
        while (R < str1.length) {
            if (--map[str1[R]] >= 0) diffCount--;

            if (diffCount == 0) { // str2的字符都补齐完了
                // 一直出窗口，直到str2的字符并不全部补齐
                while (map[str1[L]] < 0) {
                    map[str1[L++]]++;
                }

                // 如果出现最小长度
                if (R - L + 1 < minLen) {
                    minLen = R - L + 1;
                    minStart = L;
                }

                // 窗口中删除一个开头字符，即以 下个字符 为头 继续判断
                map[str1[L++]]++;
                diffCount++;
            }

            R++;
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
    }
}
