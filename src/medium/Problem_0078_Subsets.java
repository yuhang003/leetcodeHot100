package medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 78. 子集
 * https://leetcode.cn/problems/subsets
 */
public class Problem_0078_Subsets {

    public static void main(String[] args) {
        method();
    }

    public static String method() {
        try {
            System.out.println("try");
            return "";
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("finally");
        }

        return "method";
    }

    List<List<Integer>> ans;
    List<Integer> list;

    public List<List<Integer>> subsets(int[] nums) {
        ans = new ArrayList<>();
        list = new ArrayList<>();

        process(nums, 0);
        return ans;
    }

    public void process(int[] nums, int startIndex) {
        ans.add(new ArrayList<>(list));

        // 子集不讲究顺序，所以[1,2]和[2,1]是同一子集
        // 题目要求不能返回重复子集
        // 所以 i 不能从 0 开始，要从 startIndex 开始。
        // 这样才能保证不断往后找，不能回头找
        for (int i = startIndex; i < nums.length; i++) {
            list.add(nums[i]);
            process(nums, i + 1);
            list.remove(list.size() - 1);
        }
    }
}
