package medium;

/**
 * 33. 搜索旋转排序数组
 * https://leetcode.cn/problems/search-in-rotated-sorted-array/description/?favorite=2cktkvj
 */
public class Problem_0033_Search {

    public int search(int[] nums, int target) {
        int N = nums.length;
        if (N == 0) return -1;
        if (N == 1) return nums[0] == target ? 0 : -1;

        int L = 0, R = N - 1;
        int M = 0;

        while (L <= R) {
            M = L + ((R - L) >> 1);
            if (nums[M] == target) return M;

            // [L, M]有序，说明[M, R]无序
            if (nums[L] <= nums[M]) {
                // target在[L, M]范围上，接下来左侧继续二分
                if (target >= nums[L] && target < nums[M]) {
                    R = M - 1;
                } else { // 去右侧二分
                    L = M + 1;
                }
            } else { // [L, M]无序，说明[M, R]有序
                if (target > nums[M] && target <= nums[R]) { // 如果[M, R]范围上包括了target，去右侧二分
                    L = M + 1;
                } else { // 去左侧二分
                    R = M - 1;
                }
            }
        }

        return -1;
    }
}
