package medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 438. 找到字符串中所有字母异位词
 * https://leetcode.cn/problems/find-all-anagrams-in-a-string
 */
public class Problem_0438_FindAnagrams {

    public static void main(String[] args) {
        String s = "baapaa";
        String p = "aa";
        System.out.println(findAnagrams(s, p));
    }

    public static List<Integer> findAnagrams(String s, String p) {
        int sLen = s.length(), pLen = p.length();
        if (sLen < pLen) return new ArrayList<>();

        List<Integer> ans = new ArrayList<>();
        int[] count = new int[26];
        // 将前pLen长度的字符放入滑动窗口中
        for (int i = 0; i < pLen; i++) {
            count[s.charAt(i) - 'a']++;
            count[p.charAt(i) - 'a']--;
        }

        int differ = 0;
        // 计算有多少个单词不同
        for (int i = 0; i < 26; i++) {
            if (count[i] != 0) {
                differ++;
            }
        }

        if (differ == 0) ans.add(0);

        for (int i = 0; i < sLen - pLen; i++) {
            // 窗口出去一个单词
            int outIndex = s.charAt(i) - 'a';
            // 要出去了，而且该单词本身就只多一个，出去了正好就不多了，要--
            if (count[outIndex] == 1) differ--;
            // 单词本身不缺也不多，现在单词要出去，少了一个了，所以要++
            else if (count[outIndex] == 0) differ++;
            count[outIndex]--;

            // 窗口进来一个单词
            int inIndex = s.charAt(i + pLen) - 'a';
            // 如果原来就缺一个这个单词，现在进来了，那么就正好，所以--
            if (count[inIndex] == -1) differ--;
            // 单词本身不缺也不多，现在进来一个，变多了，所以++
            else if (count[inIndex] == 0) differ++;
            count[inIndex]++;

            // 滑动窗口移动一次之后，没有单词有问题，那就记录一个答案
            if (differ == 0) ans.add(i + 1);
        }

        return ans;
    }
}
