package medium;

import entity.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 437. 路径总和 III
 * https://leetcode.cn/problems/path-sum-iii
 */
public class Problem_0437_PathSum {

    public int pathSum(TreeNode root, int targetSum) {
        int ans = 0;
        if (root == null) return ans;

        ans += process(root, targetSum);
        if (root.left != null) {
            ans += pathSum(root.left, targetSum);
        }
        if (root.right != null) {
            ans += pathSum(root.right, targetSum);
        }

        return ans;
    }

    public int process(TreeNode node, long rest) {
        int ans = 0;
        if (node == null) return ans;

        int val = node.val;
        if (rest == val) {
            ans++;
        }

        ans += process(node.left, rest - val);
        ans += process(node.right, rest - val);
        return ans;
    }


    /**
     * 前缀和思想
     */
    public int pathSum2(TreeNode root, int targetSum) {
        Map<Long, Integer> preSumMap = new HashMap<>();
        preSumMap.put(0L, 1);

        return process2(root, targetSum, 0, preSumMap);
    }

    public int process2(TreeNode root, int target, long preAll, Map<Long, Integer> preSumMap) {
        if (root == null) return 0;

        int ans = 0;
        long all = preAll + root.val;
        if (preSumMap.containsKey(all - target)) {
            ans = preSumMap.get(all - target);
        }
        preSumMap.put(all, preSumMap.getOrDefault(all, 0) + 1);

        ans += process2(root.left, target, all, preSumMap);
        ans += process2(root.right, target, all, preSumMap);

        preSumMap.put(all, preSumMap.get(all) - 1);
        return ans;
    }
}
