package medium;

/**
 * https://leetcode.cn/problems/kth-largest-element-in-an-array
 * 215. 数组中的第K个最大元素
 */
public class Problem_0215_FindKthLargest {

    public int findKthLargest(int[] nums, int k) {
        return process(nums, 0, nums.length - 1, nums.length - k);
    }

    public int process(int[] nums, int L, int R, int index) {
        if (L == R) return nums[L];

        int[] range = partition(nums, L, R);
        if (index >= range[0] && index <= range[1]) {
            return nums[range[0]];
        } else if (index < range[0]) {
            return process(nums, L, range[0] - 1, index);
        }
        return process(nums, range[1] + 1, R, index);
    }

    public int[] partition(int[] nums, int L, int R) {
        int pivot = nums[L];
        int cur = L;
        int less = L - 1, more = R + 1;

        while (cur < more) {
            if (nums[cur] == pivot) {
                cur++;
            } else if (nums[cur] > pivot) {
                swap(nums, cur, --more);
            } else {
                swap(nums, cur++, ++less);
            }
        }

        return new int[]{less + 1, more - 1};
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
