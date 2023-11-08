package medium;

/**
 * 48. 旋转图像
 * https://leetcode.cn/problems/rotate-image/description/?favorite=2cktkvj
 */
public class Problem_0048_Rotate {

    public void rotate(int[][] matrix) {
        // 使用左上角(a, b)，右下角(c, d)来规划一个圈
        int a = 0, b = 0;
        int c = matrix.length - 1, d = matrix[0].length - 1;

        while (a < c) {
            // 将(a, b)和(c, d)划出来的圈，进行旋转
            // 然后将圈往里推进一圈
            rotateEdge(matrix, a++, b++, c--, d--);
        }
    }

    // 将以(a, b)和(c, d)划出来的圈，进行旋转
    public void rotateEdge(int[][] matrix, int a, int b, int c, int d) {
        int temp;
        // 将圈中每一个元素都找到对应的位置，进行旋转。
        for (int i = 0; i < d - b; i++) {
            temp = matrix[a][b + i];
            matrix[a][b + i] = matrix[c - i][b];
            matrix[c - i][b] = matrix[c][d - i];
            matrix[c][d - i] = matrix[a + i][d];
            matrix[a + i][d] = temp;
        }
    }
}
