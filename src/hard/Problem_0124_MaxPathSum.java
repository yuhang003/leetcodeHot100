package hard;

import entity.TreeNode;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

/**
 * 124. 二叉树中的最大路径和
 * https://leetcode.cn/problems/binary-tree-maximum-path-sum
 */
public class Problem_0124_MaxPathSum {

    public static void main(String[] args) {
        LocalDate date = LocalDate.now();
        System.out.println(date);
        Date startDate = getFirstDayOfYear(date);
        Date endDate = getLastDayOfPreviousMonth(date);

        System.out.println(startDate);
        System.out.println(endDate);
        date = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        System.out.println(date);
    }

    public static Date getLastDayOfPreviousMonth(LocalDate date) {
        // 使用LocalDate对象进行上个月最后一天的计算
        LocalDate lastDayOfLastMonth = date.minusMonths(1).withDayOfMonth(date.minusMonths(1).lengthOfMonth());

        // 将得到的结果LocalDate对象转换回Date对象
        return Date.from(lastDayOfLastMonth.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static Date getFirstDayOfYear(LocalDate date) {
        // 使用LocalDate对象获取当前年份
        int year = date.getYear();

        // 构造一个新的LocalDate对象，表示当前年份的第一天
        LocalDate firstDayOfYear = LocalDate.of(year, 1, 1);

        // 将得到的结果LocalDate对象转换回Date对象
        return Date.from(firstDayOfYear.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public int maxPathSum(TreeNode root) {
        if (root == null) return 0;

        return process(root).maxPathSum;
    }

    public Info process(TreeNode x) {
        if (x == null) return null;

        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);

        // 必须从x开始的路径 1)只有x 2）x往左扎 3）x往右扎
        int maxPathSumFromHead = x.val;
        if (leftInfo != null) {
            maxPathSumFromHead = Math.max(maxPathSumFromHead, x.val + leftInfo.maxPathSumFromHead);
        }
        if (rightInfo != null) {
            maxPathSumFromHead = Math.max(maxPathSumFromHead, x.val + rightInfo.maxPathSumFromHead);
        }

        // x整棵树最大路径和 1) 只有x 2)左树整体的最大路径和 3) 右树整体的最大路径和
        // 整棵树的最大路径和分为两种情况
        // (1) 最大路径不经过 x，所以x最大路径和就取 左子树或右子树的最大路径和即可
        // (2) 最大路径经过 x，分为四种小情况  1)只有x 2）x往左扎 3）x往右扎 4) 从左到右都走，不单走一边
        // 整棵树的最大路径和就是在这6中情况中取最大即可
        int maxPathSum = x.val;
        if (leftInfo != null) {
            maxPathSum = Math.max(maxPathSum, leftInfo.maxPathSum);
        }
        if (rightInfo != null) {
            maxPathSum = Math.max(maxPathSum, rightInfo.maxPathSum);
        }
        maxPathSum = Math.max(maxPathSum, maxPathSumFromHead);
        if (leftInfo != null && rightInfo != null
                && leftInfo.maxPathSumFromHead > 0 && rightInfo.maxPathSumFromHead > 0) {
            maxPathSum = Math.max(maxPathSum, x.val + leftInfo.maxPathSumFromHead + rightInfo.maxPathSumFromHead);
        }

        return new Info(maxPathSum, maxPathSumFromHead);
    }

    // 任何一个节点，必须汇报上来的信息
    public static class Info {
        int maxPathSum;
        int maxPathSumFromHead;

        public Info(int maxPathSum, int maxPathSumFromHead) {
            this.maxPathSum = maxPathSum;
            this.maxPathSumFromHead = maxPathSumFromHead;
        }
    }
}
