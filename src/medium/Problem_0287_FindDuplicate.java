package medium;

/**
 * 287. 寻找重复数
 * https://leetcode.cn/problems/find-the-duplicate-number
 */
public class Problem_0287_FindDuplicate {
    public static void main(String[] args) {
        int[] nums = {1, 3, 4, 2, 2};
        System.out.println(findDuplicate(nums));
    }

    // 快慢指针
    // 对 nums 数组建图，每个位置 i 连一条 i→nums[i] 的边。
    // 由于存在的重复的数字 target，因此 target 这个位置一定有起码两条指向它的边，
    // 因此整张图一定存在环，且我们要找到的 target 就是这个环的入口，
    // 那么整个问题就等价于 环形链表 II。
    public static int findDuplicate(int[] nums) {
        int slow = 0, fast = 0;
        // 1. 快慢指针相遇
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);

        // 2. 让快指针回到起点，快慢指针同时走，相遇的地方就是环的入口
        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }

        return slow;
    }

    // 二分法
    public int findDuplicate2(int[] nums) {
        int l = 1;
        int r = nums.length - 1;

        while (l < r) {
            int mid = l + ((r - l) >> 1);
            int count = 0;

            for (int num : nums) {
                if (num <= mid) {
                    count++;
                }
            }

            if (count > mid) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        return l;
    }
}
