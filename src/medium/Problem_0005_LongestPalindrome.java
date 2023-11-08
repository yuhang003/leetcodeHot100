package medium;

/**
 * https://leetcode.cn/problems/longest-palindromic-substring/?favorite=2cktkvj
 * 5. 最长回文子串
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
        for (int i = 0; i < charArr.length; i++) {
            res[index++] = charArr[i];
            res[index++] = '#';
        }

        return res;
    }
}
