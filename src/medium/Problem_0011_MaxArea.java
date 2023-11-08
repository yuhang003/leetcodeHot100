package medium;

/**
 * https://leetcode.cn/problems/container-with-most-water/?favorite=2cktkvj
 * 11. 盛最多水的容器
 */
public class Problem_0011_MaxArea {

    public int maxArea(int[] height) {
        if (height == null || height.length < 2) return 0;

        int left = 0, right = height.length - 1;
        int max = 0;

        while (left <= right) {
            max = Math.max(max, (right - left) * Math.min(height[left], height[right]));

            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return max;
    }
}
