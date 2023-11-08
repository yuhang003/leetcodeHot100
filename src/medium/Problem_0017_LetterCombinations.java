package medium;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/letter-combinations-of-a-phone-number/description/?favorite=2cktkvj
 * 17. 电话号码的字母组合
 */
public class Problem_0017_LetterCombinations {

    List<String> ans = new ArrayList<>();
    StringBuilder builder = new StringBuilder();

    String[] letterMap = {
            "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
    };

    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0) return ans;
        backtracking(digits, 0);
        return ans;
    }

    public void backtracking(String digits, int index) {
        if (index == digits.length()) {
            ans.add(builder.toString());
            return;
        }

        int num = digits.charAt(index) - '0';
        String letters = letterMap[num];
        for (int i = 0; i < letters.length(); i++) {
            builder.append(letters.charAt(i));
            backtracking(digits, index + 1);
            builder.deleteCharAt(builder.length() - 1);
        }
    }
}
