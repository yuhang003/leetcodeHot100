package easy;

/**
 * 二分查找
 */
public class NumXX_BinarySearch {

    public static void main(String[] args) {
        int[] nums = {1, 3, 4, 5, 5, 7, 8};
        System.out.println(findIndex(nums, 4));
        System.out.println(moreMostLeft(nums, 5));
        System.out.println(lessMostRight(nums, 6));
    }

    // 有一个有序数组，给你一个target值，找到target所在的下标
    // 假设数组中target不会重复
    public static int findIndex(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid -1;
            } else {
                left = mid + 1;
            }
        }

        return -1;
    }

    // 有一个有序数组，给你一个target值，找到>=target的最左下标（即>=target的最小值）
    public static int moreMostLeft(int[] nums, int target) {
        int left = 0, right = nums.length;
        int ans = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return ans;
    }

    // 有一个有序数组，给你一个target值，找到<=target的最右下标（即<=target的最大值）
    public static int lessMostRight(int[] nums, int target) {
        int left = 0, right = nums.length;
        int ans = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] <= target) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return ans;
    }
}
