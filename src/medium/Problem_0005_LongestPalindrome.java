package medium;

/**
 * https://leetcode.cn/problems/longest-palindromic-substring/?favorite=2cktkvj
 * 5. 最长回文子串
 *
 * 定义变量 R 是 C为中心点能拓展到的最右下标，每次 i 下标拓展的范围超过了 R，那么就将 C 更新为 i
 * 从以下几种可能分析：
 * 1、如果 i 不在 R 范围中，那就只能不断往两边拓展来判断，没有捷径
 * 2、如果 i 在  R 范围中，可以分为三种情况，首先定义几个变量，C 为 中心点，i 以 C为中心找到 i'
 *    R 以 C 为中心找到 L
 *      2.1 如果 i' 的回文字符串在L-C中间，那么i的回文半径和i'一定一样，直接得出答案
 *      2.2 如果 i' 的回文字符串不在L-C中间，那么i的回文半径，一定是R - i，直接得出答案
 *      2.3 如果 i' 的回文字符串正好压在L上，那么i的回文半径，最基本的是R - i，至于是否还会更大，
 *          那么就往两边扩展来判断。
 */
public class Problem_0005_LongestPalindrome {

    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return "";

        char[] oldChars = s.toCharArray();
        char[] str = manacherString(s);

        int[] pArr = new int[str.length];
        int C = -1, R = -1;
        int max = Integer.MIN_VALUE;
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < str.length; i++) {
            pArr[i] = R > i ? Math.min(pArr[2 * C - i], R - i) : 1;

            while (i + pArr[i] < str.length && i - pArr[i] > -1) {
                if (str[i + pArr[i]] == str[i - pArr[i]]) {
                    pArr[i]++;
                } else {
                    break;
                }
            }

            if (i + pArr[i] > R) {
                C = i;
                R = i + pArr[i];
            }

            if (max < pArr[i]) {
                max = pArr[i];

                builder.setLength(0);
                builder.append(oldChars, (i - pArr[i] + 1) >> 1, pArr[i] - 1);
            }
        }

        return builder.toString();
    }

    public char[] manacherString(String str) {
        char[] charArr = str.toCharArray();
        char[] res = new char[2 * str.length() + 1];

        int index = 0;
        res[index++] = '#';
        for (char c : charArr) {
            res[index++] = c;
            res[index++] = '#';
        }

        return res;
    }
}
