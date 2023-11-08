package medium;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/next-permutation/?favorite=2cktkvj
 * 31. 下一个排列
 */
public class Problem_0031_NextPermutation {

    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length < 2) return;

        int N = nums.length;
        // 从右往左第一次降序的位置
        int firstLess = -1;
        for (int i = N - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                firstLess = i;
                break;
            }
        }

        if (firstLess == -1) {
            reverse(nums, 0, N - 1);
        } else {
            // 找到最靠右的，同时比nums[firstLess]大的数的下标
            int rightClosestMore = -1;
            // 这里也可以使用二分进行优化
            for (int i = N - 1; i > firstLess; i--) {
                if (nums[i] > nums[firstLess]) {
                    rightClosestMore = i;
                    break;
                }
            }

            swap(nums, firstLess, rightClosestMore);
            reverse(nums, firstLess + 1, N - 1);
        }

    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
