package medium;

/**
 * 75. 颜色分类
 * https://leetcode.cn/problems/sort-colors
 */
public class Problem_0075_SortColors {

    public void sortColors(int[] nums) {
        partition(nums, 0, nums.length - 1, 1);
    }

    // 将数组[L...R]范围，划分为 <val, =val, >val 三个区域
    public void partition(int[] nums, int L, int R, int val) {
        int less = L - 1;
        int more = R + 1;
        int index = L;

        while (index < more) {
            if (nums[index] == val) {
                index++;
            } else if (nums[index] < val) {
                less++;
                swap(nums, less, index);
                index++;
            } else {
                more--;
                swap(nums, index, more);
            }
        }
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
