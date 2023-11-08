package medium;

import entity.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 538. 把二叉搜索树转换为累加树
 * https://leetcode.cn/problems/convert-bst-to-greater-tree
 *
 * 反序中序遍历
 */
public class Problem_0538_ConvertBST {

    // 递归
    int sum = 0;
    public TreeNode convertBST(TreeNode root) {
        if (root == null) return null;

        convertBST(root.right);
        sum += root.val;
        root.val = sum;
        convertBST(root.left);
        return root;
    }

    // 迭代
    public TreeNode convertBST2(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();
        int sum = 0;
        TreeNode cur = root;

        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.right;
            } else {
                cur = stack.pop();
                sum += cur.val;
                cur.val = sum;

                cur = cur.left;
            }
        }

        return root;
    }
}
