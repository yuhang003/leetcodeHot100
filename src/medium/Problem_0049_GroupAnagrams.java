package medium;

import java.util.*;

/**
 * 49. 字母异位词分组
 * https://leetcode.cn/problems/group-anagrams/?favorite=2cktkvj
 */
public class Problem_0049_GroupAnagrams {
    // 直接排序，将排序之后的字符串当key
    public List<List<String>> groupAnagrams1(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String sortStr = new String(chars);

            if (map.containsKey(sortStr)) {
                map.get(sortStr).add(str);
            } else {
                List<String> groupList = new ArrayList<>();
                groupList.add(str);
                map.put(sortStr, groupList);
            }
        }

        return new ArrayList<>(map.values());
    }

    // 将字符串中字符出现的频率转化成新的字符串，当做key
    // 例如：[b,a,a,a,b,c]，转化为 a3b2c1
    public List<List<String>> groupAnagrams2(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (String str : strs) {
            int[] counts = new int[26];
            for (char ch : str.toCharArray()) {
                counts[ch - 'a']++;
            }

            StringBuilder builder = new StringBuilder();

            for (int i = 0; i < counts.length; i++) {
                if (counts[i] != 0) {
                    builder.append((char) (i + 'a'));
                    builder.append(counts[i]);
                }
            }

            String key = builder.toString();
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(str);
            map.put(key, list);
        }

        return new ArrayList<>(map.values());
    }
}
