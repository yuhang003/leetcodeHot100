package medium;

/**
 * 647. 回文子串
 * https://leetcode.cn/problems/palindromic-substrings
 */
public class Problem_0647_CountSubstrings {

    public int countSubstrings(String s) {
        return manacher(s);
    }

    public int manacher(String s) {
        if (s == null || s.length() == 0) return 0;

        // "12132" -> "#1#2#1#3#2#"
        char[] str = manacherString(s);
        // 回文半径的大小
        int[] pArr = new int[str.length];

        // 当R被更新的时候，是哪个下标更新的，那个下标就是C，主要是为了计算对称点的下标
        // 讲述中：R代表最右的扩成功的位置，coding：最右的扩成功位置的，再下一个位置，即R是第一个扩不成功的位置
        int C = -1, R = -1;
        int ans = 0;

        for (int i = 0; i < str.length; i++) {
            // 2 * C - i得出的，就是i以C为对称中心，对称出来的i'的坐标
            // 如果i没有被R覆盖到(i >= R)，至少本身是回文串，所以为1
            // 如果被R覆盖(i < R)，那么取i'的回文半径 和 i到R的距离， 两者的最小值，最小值是i下标至少不用比的位置，下面接着往后比就行
            pArr[i] = i < R ? Math.min(pArr[2 * C - i], R - i) : 1;

            // 开始尝试往外扩
            while (i + pArr[i] < str.length && i - pArr[i] >= 0) {
                // 往外扩之后还是回文，回文半径++
                if (str[i + pArr[i]] == str[i - pArr[i]]) {
                    pArr[i]++;
                } else { // 扩了之后不是回文，i位置的回文半径结束，进行下一次递归
                    break;
                }
            }

            // 如果扩完之后的位置超过了 R，那么就更新R以及C
            if (i + pArr[i] > R) {
                R = i + pArr[i];
                C = i;
            }

            ans += (pArr[i] >> 1);
        }

        return ans;
    }

    public char[] manacherString(String s) {
        int n = s.length();
        char[] chars = new char[2 * n + 1];

        int index = 0;
        chars[index++] = '#';
        for (char ch : s.toCharArray()) {
            chars[index++] = ch;
            chars[index++] = '#';
        }

        return chars;
    }
}
