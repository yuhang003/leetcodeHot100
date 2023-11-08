package medium;

import entity.TreeNode;

/**
 * 114. 二叉树展开为链表
 * https://leetcode.cn/problems/flatten-binary-tree-to-linked-list
 */
public class Problem_0114_Flatten {

    // 寻找前驱结点方法
    // 因为是前序遍历，所以最终结果中，父节点的右子树的前驱结点一定是左子树的最右节点
    // 所以每遍历到一个节点，就找该节点左子树的最右节点，将该节点的右子树放到最右节点下面
    // 因为要求最终结果是左子树为null，右子树为前序遍历结果，所以将左节点放到右节点上，左节点置null
    public void flatten(TreeNode root) {
        TreeNode cur = root;
        while (cur != null) {
            if (cur.left != null) {
                TreeNode node = cur.left;

                while (node.right != null) {
                    node = node.right;
                }

                node.right = cur.right;
                cur.right = cur.left;
                cur.left = null;
            }
            cur = cur.right;
        }
    }

    // 采用前序遍历逆序的方式
    // 递归采用的是栈，先进后出
    // 所以会先找到最后一个结点，将其记录下来，然后会出栈，出栈的节点的right指向上一个节点就行，left指向null
    // 然后将当前节点记录，供下一个节点来指向，最终前序遍历逆序遍历完所有节点后，即可得到前序遍历的链表
    private TreeNode prev = null;
    public void flatten2(TreeNode root) {
        if (root == null) return;

        flatten2(root.right);
        flatten2(root.left);
        root.right = prev;
        root.left = null;
        prev = root;
    }


    /**
     * 第三种方法
     * 就是定义一个process(x)方法，返回一个Info信息
     * 该Info信息就是以x为头结点，转化为链表之后的链表的头结点和尾节点
     * 这样一层层递归，就能得到最终root结点为头结点的树转化成的链表
     *
     * 而且可以得到链表的头结点，以及可以转化为双向链表，都没有问题
     * 更好理解
     * @param root
     */
    public void flatten3(TreeNode root) {
        if (root == null) return;

        process(root);
    }

    public Info process(TreeNode root) {
        if (root == null) return null;

        Info leftInfo = process(root.left);
        Info rightInfo = process(root.right);

        Info rootInfo = new Info(root, root);
        if (leftInfo != null) {
            root.right = leftInfo.head;
            rootInfo.tail = leftInfo.tail;
        }

        if (rightInfo != null) {
            rootInfo.tail.right = rightInfo.head;
            rootInfo.tail = rightInfo.tail;
        }
        root.left = null;

        return rootInfo;
    }

    class Info {
        public TreeNode head;
        public TreeNode tail;

        public Info(TreeNode h, TreeNode t) {
            head = h;
            tail = t;
        }
    }
}
