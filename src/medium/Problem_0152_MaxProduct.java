package medium;

/**
 * 152. 乘积最大子数组
 * https://leetcode.cn/problems/maximum-product-subarray
 */
public class Problem_0152_MaxProduct {

    public int maxProduct(int[] nums) {
        // 记录以 0 为结尾的子数组的最大累乘积
        int preMax = nums[0];
        // 记录以 0 为结尾的子数组的最小累乘积
        int preMin = nums[0];
        int ans = nums[0];

        for (int i = 1; i < nums.length; i++) {
            // 记录三种可能性
            int p1 = nums[i];
            int p2 = nums[i] * preMax;
            int p3 = nums[i] * preMin;

            // 从三种可能性中取最大累乘积和最小累乘积
            int curMax = Math.max(p1, Math.max(p2, p3));
            int curMin = Math.min(p1, Math.min(p2, p3));
            // 将最大累乘积与ans比较
            ans = Math.max(ans, curMax);

            // 更新以 i 为结尾的子数组的最大累乘积和最小累乘积
            preMax = curMax;
            preMin = curMin;
        }

        return ans;
    }

    // 非空间优化版本
    public int maxProduct2(int[] nums) {
        int n = nums.length;
        int[] dpMax = new int[n];
        int[] dpMin = new int[n];

        // 记录以 0 为结尾的子数组的最大累乘积和最小累乘积
        dpMax[0] = nums[0];
        dpMin[0] = nums[0];
        int ans = nums[0];

        for (int i = 1; i < nums.length; i++) {
            // 记录三种可能性
            int p1 = nums[i];
            int p2 = nums[i] * dpMax[i - 1];
            int p3 = nums[i] * dpMin[i - 1];

            // 从三种可能性中取最大累乘积和最小累乘积
            int curMax = Math.max(p1, Math.max(p2, p3));
            int curMin = Math.min(p1, Math.min(p2, p3));
            // 将最大累乘积与ans比较
            ans = Math.max(ans, curMax);

            // 更新以 i 为结尾的子数组的最大累乘积和最小累乘积
            dpMax[i] = curMax;
            dpMin[i] = curMin;
        }

        return ans;
    }
}
