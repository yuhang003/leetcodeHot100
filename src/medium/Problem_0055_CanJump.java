package medium;

/**
 * 55. 跳跃游戏
 * https://leetcode.cn/problems/jump-game/?favorite=2cktkvj
 */
public class Problem_0055_CanJump {

    public boolean canJump(int[] nums) {
        // 当前能走到的最大下标
        int cover = 0;
        for (int i = 0; i <= cover; i++) {
            cover = Math.max(cover, nums[i] + i);

            if (cover >= nums.length - 1) {
                return true;
            }
        }

        return false;
    }
}
