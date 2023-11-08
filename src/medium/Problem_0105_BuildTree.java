package medium;

import entity.TreeNode;

/**
 * 105. 从前序与中序遍历序列构造二叉树
 * https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal
 *
 * 方法2：从后序与中序遍历序列构造二叉树
 * 不能只给前序和后序构造二叉树
 */
public class Problem_0105_BuildTree {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length != inorder.length) {
            return null;
        }

        return buildTreeHelper(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    // 前序遍历数组中[preStart, preEnd]范围上构建二叉树
    // 中序遍历数组中[inStart, inEnd]范围上构建二叉树
    private TreeNode buildTreeHelper(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }

        // 前序遍历数组中，第一个元素是头结点
        int rootVal = preorder[preStart];
        TreeNode root = new TreeNode(rootVal);

        // idx 用来记录头结点在 中序遍历数组中的下标
        int idx = inStart;
        for (int i = inStart; i <= inEnd; i++) {
            if (rootVal == inorder[i]) {
                idx = i;
                break;
            }
        }

        // 头结点左边有多少个结点
        int leftLen = idx - inStart;
        // 对于前序遍历来说，从头结点的下一个节点开始，一直到 preStart + leftLen 都属于头结点的左子树节点
        // 对于中序遍历来说，从 inStart 开始，到 头结点左边的节点下标(idx - 1) 结束，都属于头结点的左子树节点
        root.left = buildTreeHelper(preorder, preStart + 1, preStart + leftLen, inorder, inStart, idx - 1);
        // 对于前序遍历来说，从 preStart + leftLen + 1 开始，一直到 结尾下标(preEnd) 结束，都属于头结点的右子树节点
        // 对于中序遍历来说，从 idx + 1 开始，到 inEnd 结束，都属于头结点的右子树节点
        root.right = buildTreeHelper(preorder, preStart + leftLen + 1, preEnd, inorder, idx + 1, inEnd);

        return root;
    }


    // 题目延伸：给你一个后序和中序遍历，构建一颗二叉树
    public TreeNode buildTree2(int[] postorder, int[] inorder) {
        if (postorder == null || inorder == null || postorder.length != inorder.length) {
            return null;
        }

        return buildTreeHelper2(postorder, 0, postorder.length - 1, inorder, 0, inorder.length - 1);
    }

    private TreeNode buildTreeHelper2(int[] postorder, int postStart, int postEnd, int[] inorder, int inStart, int inEnd) {
        if (postStart > postEnd || inStart > inEnd) {
            return null;
        }

        // 后序遍历数组中，最后第一个元素是头结点
        int rootVal = postorder[postEnd];
        TreeNode root = new TreeNode(rootVal);

        // idx 用来记录头结点在 中序遍历数组中的下标
        int idx = inStart;
        for (int i = inStart; i <= inEnd; i++) {
            if (rootVal == inorder[i]) {
                idx = i;
                break;
            }
        }

        // 头结点左边有多少个结点
        int leftLen = idx - inStart;
        // 对于后序遍历来说，从 postStart 开始，一直到 postStart + leftLen - 1 都属于头结点的左子树节点
        // 对于中序遍历来说，从 inStart 开始，到 头结点左边的节点下标(idx - 1) 结束，都属于头结点的左子树节点
        root.left = buildTreeHelper2(postorder, postStart, postStart + leftLen - 1, inorder, inStart, idx - 1);
        // 对于后序遍历来说，从 postStart + leftLen 开始，一直到 结尾元素的前一个下标(postEnd - 1) 结束，都属于头结点的右子树节点
        // 对于中序遍历来说，从 idx + 1 开始，到 inEnd 结束，都属于头结点的右子树节点
        root.right = buildTreeHelper2(postorder, postStart + leftLen, postEnd - 1, inorder, idx + 1, inEnd);
        return root;
    }
}
