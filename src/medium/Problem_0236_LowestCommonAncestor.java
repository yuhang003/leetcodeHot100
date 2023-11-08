package medium;

import entity.TreeNode;

/**
 * 236. 二叉树的最近公共祖先
 * https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree
 */
public class Problem_0236_LowestCommonAncestor {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return process(root, p, q).ans;
    }

    public Info process(TreeNode node, TreeNode a, TreeNode b) {
        if (node == null) return new Info(false, false, null);

        Info leftInfo = process(node.left, a, b);
        Info rightInfo = process(node.right, a, b);

        boolean findA = a == node || leftInfo.findA || rightInfo.findA;
        boolean findB = b == node || leftInfo.findB || rightInfo.findB;
        TreeNode ans = null;

        if (leftInfo.ans != null) {
            ans = leftInfo.ans;
        } else if (rightInfo.ans != null) {
            ans = rightInfo.ans;
        } else {
            if (findA && findB) {
                ans = node;
            }
        }

        return new Info(findA, findB, ans);
    }


    class Info {
        public boolean findA;
        public boolean findB;
        public TreeNode ans;

        public Info(boolean findA, boolean findB, TreeNode ans) {
            this.findA = findA;
            this.findB = findB;
            this.ans = ans;
        }
    }
}
