package medium;

/**
 * https://leetcode.cn/problems/implement-trie-prefix-tree
 * 208. 实现 Trie (前缀树)
 */
public class Problem_0208_Trie {
    class Trie {
        Node root;

        public Trie() {
            root = new Node();
        }

        public void insert(String word) {
            char[] chars = word.toCharArray();

            Node cur = root;
            for (char c : chars) {
                Node node = cur.next[c - 'a'];
                if (node == null) {
                    cur.next[c - 'a'] = new Node();
                    node = cur.next[c - 'a'];
                }
                cur = node;
            }
            cur.isEnd = true;
        }

        public boolean search(String word) {
            char[] chars = word.toCharArray();

            Node cur = root;
            for (char c : chars) {
                Node node = cur.next[c - 'a'];
                if (node == null) {
                    return false;
                }
                cur = node;
            }

            return cur.isEnd;
        }

        public boolean startsWith(String prefix) {
            char[] chars = prefix.toCharArray();

            Node cur = root;
            for (char c : chars) {
                Node node = cur.next[c - 'a'];
                if (node == null) {
                    return false;
                }
                cur = node;
            }

            return true;
        }
    }

    class Node {
        public boolean isEnd;
        public Node[] next;

        public Node() {
            next = new Node[26];
            isEnd = false;
        }
    }
}
