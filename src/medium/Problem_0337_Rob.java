package medium;

import entity.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 打家劫舍（1 -- 3）
 * https://leetcode.cn/problems/house-robber-iii
 */
public class Problem_0337_Rob {

    // 198. 打家劫舍
    // 动态规划，从左到右尝试模型
    class Rob1 {
        // 可以将一维数组优化为两个变量
        public int rob(int[] nums) {
            if (nums.length == 1) return nums[0];

            int N = nums.length;
            int[] dp = new int[N];
            // 初始化dp数组
            dp[0] = nums[0];
            dp[1] = Math.max(nums[0], nums[1]);

            for (int i = 2; i < N; i++) {
                // 判断是否偷当前屋子
                dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
            }

            return dp[N - 1];
        }
    }

    // 213. 打家劫舍 II
    // 动态规划，从左到右尝试模型
    // 多思考一下开头和结尾的房子即可
    class Rob2 {
        public int rob(int[] nums) {
            if (nums.length == 1) return nums[0];

            int n = nums.length;
            // 不考虑结尾
            int ans1 = process(nums, 0, n - 2);
            // 不考虑开头
            int ans2 = process(nums, 1, n - 1);
            // 两者取最大值，避开了环的限制
            return Math.max(ans1, ans2);
        }

        public int process(int[] nums, int start, int end) {
            if (start == end) return nums[start];

            int[] dp = new int[end + 1];
            dp[start] = nums[start];
            dp[start + 1] = Math.max(nums[start], nums[start + 1]);

            for (int i = start + 2; i <= end; i++) {
                dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
            }

            return dp[end];
        }
    }

    // 337. 打家劫舍 III
    // 动态规划，从左到右尝试模型，树形dp，其实就是在树上做动态规划
    // 需要使用后序遍历，先得到左右子树的结果，才能得到根节点的结果
    class Rob3 {
        // 思路1：直接暴力递归，并加map做记忆法搜索，否则超时过不了
        // 时间复杂度：O(n)
        // 空间复杂度：O(log n)，算上递推系统栈的空间
        public int rob(TreeNode root) {
            return process(root, new HashMap<>());
        }

        public int process(TreeNode root, Map<TreeNode, Integer> map) {
            if (root == null) return 0;
            if (map.containsKey(root)) return map.get(root);

            // 偷父节点
            int val1 = root.val;
            // 因为偷了父节点，所以只能去偷孙子节点
            if (root.left != null) val1 += process(root.left.left, map) + process(root.left.right, map);
            if (root.right != null) val1 += process(root.right.left, map) + process(root.right.right, map);

            // 不偷父节点
            // 所以可以偷子节点
            int val2 = process(root.left, map) + process(root.right, map);

            int ans = Math.max(val1, val2);
            map.put(root, ans);

            return ans;
        }

        // 思路2：动态规划
        // 设计一个长度为2的数组，0下标代表不偷该节点获得的最大金额，1下标代表偷该节点获得的最大金额
        // 每个节点都返回一个数组，父节点通过是否偷窃自己，来取用不同的元素得到自己的最大金额
        // 这样一步步，最终得到根节点的最大金额
        // 需要后序遍历
        public int rob2(TreeNode root) {
            int[] ans = process2(root);
            return Math.max(ans[0], ans[1]);
        }

        public int[] process2(TreeNode root) {
            if (root == null) return new int[]{0, 0};

            // 拿到左右子节点的结果数组
            int[] left = process2(root.left); // 左
            int[] right = process2(root.right); // 右

            // 中
            // 偷父节点，子节点就不能偷，取0下标的结果
            int val1 = root.val + left[0] + right[0];

            // 不偷父节点，子节点就可以随便选择偷或者不偷
            int val2 = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);

            return new int[]{val2, val1};
        }
    }
}
