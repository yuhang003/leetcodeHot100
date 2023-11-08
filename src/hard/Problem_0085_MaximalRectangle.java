package hard;

/**
 * 85. 最大矩形
 * https://leetcode.cn/problems/maximal-rectangle
 *
 * 思路：本题将遍历每一层，从第一层开始，然后是第1，2层，然后是1, 2, 3层，一直到1, 2, ... n层
 * 当于每一层都会做一次底。每一次遍历都会拿到一个新的高数组，然后根据根据这个高数组
 * 执行一遍题3，得到最大值，每一层都取最大值，最终得到的最大值就是结果
 */
public class Problem_0085_MaximalRectangle {

    public int maximalRectangle(char[][] matrix) {
        if (matrix== null || matrix.length == 0 || matrix[0].length == 0) return 0;

        int maxArea = 0;
        int[] heights = new int[matrix[0].length];
        for (char[] chars : matrix) {
            for (int j = 0; j < matrix[0].length; j++) {
                heights[j] = chars[j] == '0' ? 0 : heights[j] + 1;
            }

            maxArea = Math.max(maxArea, largestRectangleArea(heights));
        }

        return maxArea;
    }

    // 和84题一样的方法
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) return 0;

        int len = heights.length;
        int[] stack = new int[len];
        int index = -1;
        int maxArea = 0;

        for (int i = 0; i < len; i++) {
            while (index != -1 && heights[stack[index]] >= heights[i]) {
                int j = stack[index--];

                int width = i - 1 - (index == -1 ? -1 : stack[index]);
                maxArea = Math.max(maxArea, width * heights[j]);
            }
            stack[++index] = i;
        }

        while (index != -1) {
            int j = stack[index--];
            int width = len - 1 - (index == -1 ? -1 : stack[index]);
            maxArea = Math.max(maxArea, width * heights[j]);
        }

        return maxArea;
    }
}
