package hard;

/**
 * 84. 柱状图中最大的矩形
 * https://leetcode.cn/problems/largest-rectangle-in-histogram
 */
public class Problem_0084_LargestRectangleArea {

    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) return 0;

        int size = heights.length;
        int[] stack = new int[size];
        int maxArea = 0;
        int index = -1;

        for (int i = 0; i < size; i++) {
            while (index != -1 && heights[stack[index]] >= heights[i]) {
                int j = stack[index--];

                // 以 j 为下标的高height[j]，组成的最大矩形的宽: 如果栈为空，则宽为[0, i - 1]，距离为 i
                // 如果栈不为空，则宽为[stack.peek() + 1, i - 1]，距离为 i - 1 - stack.peek()
                int width = i - 1 - (index == -1 ? -1 : stack[index]);
                maxArea = Math.max(maxArea, width * heights[j]);
            }
            stack[++index] = i;
        }

        // 如果最终还有元素没有出栈，说明现在还在栈中的元素，右边都没有比它小的数字了
        // 所以宽为[stack.peek() + 1, heights.length - 1]
        while (index != -1) {
            int j = stack[index--];
            int width = size - 1 - (index == -1 ? -1 : stack[index]);
            maxArea = Math.max(maxArea, width * heights[j]);
        }

        return maxArea;
    }
}
