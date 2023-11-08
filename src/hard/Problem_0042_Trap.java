package hard;

/**
 * 42. 接雨水
 * https://leetcode.cn/problems/trapping-rain-water
 *
 * 方法1：
 */
public class Problem_0042_Trap {

    public int trap(int[] height) {
        int n = height.length;
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];

        leftMax[0] = height[0];
        rightMax[n - 1] = height[n - 1];

        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans += (Math.min(leftMax[i], rightMax[i]) - height[i]);
        }

        return ans;
    }
}
