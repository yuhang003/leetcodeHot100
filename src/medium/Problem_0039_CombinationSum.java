package medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 39. 组合总和
 * https://leetcode.cn/problems/combination-sum/?favorite=2cktkvj
 */
public class Problem_0039_CombinationSum {
    List<List<Integer>> ans;
    List<Integer> list;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        ans = new ArrayList<>();
        list = new ArrayList<>();

        // 从下标0开始，需要凑齐target的数
        process(candidates, target, 0);

        return ans;
    }

    // rest：剩下要凑齐的数字
    // startIndex：从哪个下标开始，继续拿值尝试
    public void process(int[] candidates, int rest, int startIndex) {
        // 剩余要凑的数字为0，说明target已经达到了，放进结果集合中
        if (rest == 0) {
            ans.add(new ArrayList<>(list));
            return;
        }

        // 从startIndex下标开始取值尝试
        for (int i = startIndex; i < candidates.length; i++) {
            // 如果当前值 > 剩下要凑齐的数字，那这个值就不用考虑了
            if (candidates[i] <= rest) {
                // 先将值放进数组
                list.add(candidates[i]);
                // 去递归找剩下要凑齐的rest - candidates[i]值
                // 因为每个数可以无限取，所以下次尝试还是从 i 开始，而不是 i + 1
                process(candidates, rest - candidates[i], i);
                // 将刚才放进去的值删除，回溯
                list.remove(list.size() - 1);
            }
        }
    }
}
