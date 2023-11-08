package hard;

import java.util.ArrayList;
import java.util.List;

/**
 * 301. 删除无效的括号
 * https://leetcode.cn/problems/remove-invalid-parentheses/
 */
public class Problem_0301_RemoveInvalidParentheses {

    public List<String> removeInvalidParentheses(String s) {
        List<String> ans = new ArrayList<>();
        remove(s, ans, 0, 0, new char[]{'(', ')'});
        return ans;
    }

    // deleteIndex <= checkIndex
    // 只查s[checkIndex....]的部分，因为之前的一定已经调整对了
    // 但是之前的部分是怎么调整对的，调整的是哪个下标？就是deleteIndex
    // 比如：
    // ( ( ) ( ) ) ) ...
    // 0 1 2 3 4 5 6
    // 一开始当然checkIndex = 0，deleteIndex = 0
    // 当查到6的时候，发现不对了，
    // 然后可以去掉2位置、4位置的 )，都可以
    // 如果去掉2位置的 ), 那么下一步就是
    // ( ( ( ) ) ) ...
    // 0 1 2 3 4 5 6
    // checkIndex = 6 ，deleteIndex = 2
    // 如果去掉4位置的 ), 那么下一步就是
    // ( ( ) ( ) ) ...
    // 0 1 2 3 4 5 6
    // checkIndex = 6 ，deleteIndex = 4
    // 也就是说，
    // checkIndex和deleteIndex，分别表示查的开始 和 调的开始，之前的都不用管了  
    // par  ['(', ')']
    public void remove(String s, List<String> ans, int checkIndex, int deleteIndex, char[] par) {
        for (int count = 0, i = checkIndex; i < s.length(); i++) {
            if (s.charAt(i) == par[0]) {
                count++;
            }
            if (s.charAt(i) == par[1]) {
                count--;
            }

            // i count计数<0的第一个位置
            // 只要有违规就把前缀调整对
            if (count < 0) {
                for (int j = deleteIndex; j <= i; j++) {
                    // 剪枝，不用删除与前面一样的括号，因为删除了之后最终结果是一样的
                    // ( ) ) 后面两个括号，删除哪一个最后得到的结果都一样
                    if (s.charAt(j) == par[1] && (j == deleteIndex || s.charAt(j - 1) != par[1])) {
                        remove(s.substring(0, j) + s.substring(j + 1), ans, i, j, par);
                    }
                }
                return;
            }
        }

        // 上面的处理默认都是判断又没有多的右括号，有的话就删除
        // 将字符串反转，并找有没有多的左括号，有的话删除左括号
        String reversed = new StringBuilder(s).reverse().toString();
        if (par[0] == '(') {
            remove(reversed, ans, 0, 0, new char[]{')', '('});
        } else {
            // 最终结果是左右括号一定是有效的
            ans.add(reversed);
        }
    }
}
