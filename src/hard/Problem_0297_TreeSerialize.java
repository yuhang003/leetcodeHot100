package hard;

import entity.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 297. 二叉树的序列化与反序列化
 * https://leetcode.cn/problems/serialize-and-deserialize-binary-tree
 *
 * 使用先序遍历，进行序列化与反序列化
 */
public class Problem_0297_TreeSerialize {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        StringBuilder builder = new StringBuilder();
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();

            if (node != null) {
                builder.append(node.val).append(",");
                queue.add(node.left);
                queue.add(node.right);
            } else {
                builder.append("None,");
            }
        }

        return builder.deleteCharAt(builder.length() - 1).toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if ("None".equals(data)) return null;

        String[] dataArr = data.split(",");
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.parseInt(dataArr[0]));
        queue.add(root);

        int index = 1;
        while (index < dataArr.length - 2 && !queue.isEmpty()) {
            TreeNode node = queue.poll();
            String leftVal = dataArr[index++];
            String rightVal = dataArr[index++];

            if (!"None".equals(leftVal)) {
                TreeNode left = new TreeNode(Integer.parseInt(leftVal));
                node.left = left;
                queue.add(left);
            }

            if (!"None".equals(rightVal)) {
                TreeNode right = new TreeNode(Integer.parseInt(rightVal));
                node.right = right;
                queue.add(right);
            }
        }

        return root;
    }
}
