package medium;

import entity.TreeNode;

/**
 * 98. 验证二叉搜索树
 * https://leetcode.cn/problems/validate-binary-search-tree
 */
public class Problem_0098_IsValidBST {

    // 用来记录前一个节点
    TreeNode pre;
    public boolean isValidBST2(TreeNode root) {
        if (root == null) return true;

        // 左
        boolean left = isValidBST2(root.left);

        // 中
        // 如果不递增了
        if (pre != null && pre.val >= root.val) return false;
        pre = root;

        // 右
        boolean right = isValidBST2(root.right);

        return left && right;
    }

    public boolean isValidBST(TreeNode root) {
        return process(root).isBST;
    }

    public Info process(TreeNode node) {
        if (node == null) return null;

        // 从左右子树中获取信息
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);

        boolean isBST = true;
        int min = node.val;
        int max = node.val;

        // 构建当前节点的信息
        if (leftInfo != null) {
            max = Math.max(leftInfo.max, max);
            min = Math.min(leftInfo.min, min);

            if (!leftInfo.isBST || leftInfo.max >= node.val) {
                isBST = false;
            }
        }
        if (rightInfo != null) {
            max = Math.max(rightInfo.max, max);
            min = Math.min(rightInfo.min, min);

            if (!rightInfo.isBST || rightInfo.min <= node.val) {
                isBST = false;
            }
        }

        return new Info(isBST, min, max);
    }

    class Info {
        boolean isBST;
        int min;
        int max;

        public Info(boolean isBST, int min, int max) {
            this.isBST = isBST;
            this.min = min;
            this.max = max;
        }
    }
}
