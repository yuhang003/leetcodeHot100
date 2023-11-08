package medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 46. 全排列
 * https://leetcode.cn/problems/permutations/?favorite=2cktkvj
 */
public class Problem_0046_Permute {
    List<List<Integer>> ans;
    List<Integer> list;

    public List<List<Integer>> permute(int[] nums) {
        ans = new ArrayList<>();
        list = new ArrayList<>();

        // 用于标记 nums 中元素是否被使用过
        boolean[] used = new boolean[nums.length];
        process(nums, used);

        return ans;
    }

    public void process(int[] nums, boolean[] used) {
        // 集合大小等于数组长度，说明一个结果被找到了，可以结束本次递归了
        if (list.size() == nums.length) {
            ans.add(new ArrayList<>(list));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            // 如果该元素在本次递归没有被使用过
            if (!used[i]) {
                list.add(nums[i]);
                used[i] = true;
                process(nums, used);
                list.remove(list.size() - 1);
                used[i] = false;
            }
        }
    }
}
