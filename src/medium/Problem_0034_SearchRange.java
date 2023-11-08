package medium;

/**
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 * https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array/description/?favorite=2cktkvj
 */
public class Problem_0034_SearchRange {

    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[]{-1, -1};

        // lessMostRight得到小于target的最大值下标，如果nums中有target，该下标的下一个（+1）应该就是target的下标
        int start = lessMostRight(nums, target, 0, nums.length - 1) + 1;
        // 如果数组越界了，或者下一个下标并不是target，说明数组中没有target元素
        if (start == nums.length || nums[start] != target) return new int[]{-1, -1};

        // 得到小于 target + 1 的最大值下标，就是target的最右边下标
        int end = lessMostRight(nums, target + 1, start, nums.length - 1);
        return new int[]{start, end};
    }

    // 找到nums数组中，小于target的最右边的值的下标（因为数组不递减，也就是得到小于target的最大值下标）
    public int lessMostRight(int[] nums, int target, int start, int end) {
        int mid = 0;
        int ans = -1;
        while (start <= end) {
            mid = start + ((end - start) >> 1);

            if (nums[mid] < target) {
                start = mid + 1;
                ans = mid;
            } else {
                end = mid - 1;
            }
        }

        return ans;
    }
}
