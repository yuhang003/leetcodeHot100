package medium;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 139. 单词拆分
 * https://leetcode.cn/problems/word-break
 */
public class Problem_0139_WordBreak {

    public static boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);

        int len = s.length();
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;

        for (int i = 1; i <= len; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && set.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[len];
    }

    class Node  {
        public boolean end;
        public Node[] nexts;

        public Node() {
            end = false;
            nexts = new Node[26];
        }
    }

    public boolean wordBreak2(String s, List<String> wordDict) {
        Node root = new Node();
        // 构建前缀树
        for (String str : wordDict) {
            char[] chars = str.toCharArray();
            Node node = root;
            int index = 0;
            for (char ch : chars) {
                index = ch - 'a';
                if (node.nexts[index] == null) {
                    node.nexts[index] = new Node();
                }
                node = node.nexts[index];
            }
            node.end = true;
        }

        int N = s.length();
        char[] str = s.toCharArray();
        // 这个dp的含义和方法1的不同
        // dp[i]，表示从 i 到 N - 1下标的元素，是否可以被构建
        boolean[] dp = new boolean[N + 1];
        dp[N] = true;

        for (int i = N - 1; i >= 0; i--) {
            Node cur = root;
            for (int end = i; end < N; end++) {
                cur = cur.nexts[str[end] - 'a'];
                if (cur == null) break;

                if (cur.end) dp[i] = dp[end + 1];

                if (dp[i]) break;
            }
        }

        return dp[0];
    }
}



