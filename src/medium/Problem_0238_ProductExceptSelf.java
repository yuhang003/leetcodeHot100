package medium;

/**
 * 238. 除自身以外数组的乘积
 * https://leetcode.cn/problems/product-of-array-except-self
 */
public class Problem_0238_ProductExceptSelf {
    /**
     * 空间复杂度O(N)解法
     * 初始化两个空数组 L 和 R。对于给定索引 i，L[i] 代表的是 i 左侧所有数字的乘积，R[i] 代表的是 i 右侧所有数字的乘积。
     * 我们需要用两个循环来填充 L 和 R 数组的值。对于数组 L，L[0] 应该是 1，因为第一个元素的左边没有元素。对于其他元素：L[i] = L[i-1] * nums[i-1]。
     * 同理，对于数组 R，R[length-1] 应为 1。length 指的是输入数组的大小。其他元素：R[i] = R[i+1] * nums[i+1]。
     * 当 R 和 L 数组填充完成，我们只需要在输入数组上迭代，且索引 i 处的值为：L[i] * R[i]。
     */
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] L = new int[n];
        int[] R = new int[n];

        L[0] = 1;
        for (int i = 1; i < n; i++) {
            L[i] = L[i - 1] * nums[i - 1];
        }

        R[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            R[i] = R[i + 1] * nums[i + 1];
        }

        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = L[i] * R[i];
        }

        return ans;
    }

    /**
     * 空间复杂度O(1)解法
     * 初始化 ans 数组，对于给定索引 i，ans[i] 代表的是 i 左侧所有数字的乘积。
     * 构造方式与之前相同，只是我们试图节省空间，先把 ans 作为方法一的 L 数组。
     * 这种方法的唯一变化就是我们没有构造 R 数组。而是用一个遍历来跟踪右边元素的乘积。
     * 并更新数组 ans[i]=ans[i]∗R。然后 R 更新为 R=R∗nums[i]，其中变量 R 表示的就是索引右侧数字的乘积。
     */
    public int[] productExceptSelf2(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];

        ans[0] = 1;
        for (int i = 1; i < n; i++) {
            ans[i] = ans[i - 1] * nums[i - 1];
        }

        int R = 1;
        for (int i = n - 1; i >= 0; i--) {
            ans[i] = R * ans[i];
            R *= nums[i];
        }

        return ans;
    }
}
