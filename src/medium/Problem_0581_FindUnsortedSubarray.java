package medium;

/**
 * 581. 最短无序连续子数组
 * https://leetcode.cn/problems/shortest-unsorted-continuous-subarray
 */
public class Problem_0581_FindUnsortedSubarray {

    // 使用两次循环
    // 第一次循环找到无序子数组的右边界
    // 第二次循环找到无序子数组的左边界
    public int findUnsortedSubarray(int[] nums) {
        if (nums == null || nums.length == 1) return 0;

        int len = nums.length;
        int max = nums[0], maxIndex = 0;
        int min = nums[len - 1], minIndex = len - 1;

        // 找到无序的右边界
        for (int i = 1; i < len; i++) {
            if (nums[i] >= max) {
                // 当前值比前面的最大值要大，说明递增，只需要更新最大值就行
                max = nums[i];
            } else {
                // 当前值比前面的最大值要小，说明并没有递增，该下标肯定在无序子数组中
                maxIndex = i;
            }
        }
        // 下标还是初始值，说明数组本身已经是排好序的数组，所以不存在无序子数组
        if (maxIndex == 0) return 0;

        // 找到无序的左边界
        for (int i = len - 2; i >= 0; i--) {
            if (nums[i] <= min) {
                // 当前值比后面的最小值还小，说明数组整体递增，只需要继续更新最小值就行
                min = nums[i];
            } else {
                // 当前值比后面的最小值大，说明数组没有递增，该下标肯定在无序子数组中
                minIndex = i;
            }
        }

        return maxIndex - minIndex + 1;
    }
}
