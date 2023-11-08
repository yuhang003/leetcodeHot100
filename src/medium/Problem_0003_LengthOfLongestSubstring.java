package medium;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/longest-substring-without-repeating-characters/?favorite=2cktkvj
 * 3. 无重复字符的最长子串
 */
public class Problem_0003_LengthOfLongestSubstring {

    public int lengthOfLongestSubstring(String s) {
        int r = 0;
        Map<Character, Integer> map = new HashMap<>();

        char[] str = s.toCharArray();

        int ans = 0;
        int left = 0;
        for (int i = 0; i < str.length; i++) {
            if (map.containsKey(str[i])) {
                left = Math.max(left, map.get(str[i]) + 1);
            }
            map.put(str[i], i);
            ans = Math.max(ans, i - left + 1);
        }

        return ans;
    }
}
