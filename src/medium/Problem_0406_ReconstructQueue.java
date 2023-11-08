package medium;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 406. 根据身高重建队列
 * 测试链接：https://leetcode.cn/problems/queue-reconstruction-by-height
 *
 * 这种二维数组重排序的问题，往往可以将数组按照某一维度升序再降序，或者相反的方式来，简化问题
 */
public class Problem_0406_ReconstructQueue {

    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, (a, b) -> {
            if (a[0] != b[0]) { // 按身高降序排列
                return b[0] - a[0];
            } else { // 身高相等，按排在前面的人数升序排列
                return a[1] - b[1];
            }
        });

        List<int[]> ans = new LinkedList<>();
        for (int[] person : people) {
            ans.add(person[1], person);
        }

        return ans.toArray(new int[people.length][2]);
    }
}
