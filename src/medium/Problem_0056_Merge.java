package medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 56. 合并区间
 * https://leetcode.cn/problems/merge-intervals/description/?favorite=2cktkvj
 */
public class Problem_0056_Merge {

    public int[][] merge(int[][] intervals) {
        // 以数组左区间进行排序，排序之后，能合并的区间，下标一定都相邻
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            // 记录最小值 和 目前已经合并的最大值
            int min = intervals[i][0];
            int max = intervals[i][1];
            // 如果下一区间不越界 且 目前已经合并的最大值 >= 下一区间的左区间，说明需要继续合并
            while (i + 1 < intervals.length && max >= intervals[i + 1][0]) {
                // 更新已经合并的最大值
                max = Math.max(max, intervals[i + 1][1]);
                i++;
            }

            // 合并已经结束，最大值最小值都找到了
            int[] arr = new int[2];
            arr[0] = min;
            arr[1] = max;
            list.add(arr);
        }

        // 将 list 转化为 数组
        int[][] ans = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            ans[i][0] = list.get(i)[0];
            ans[i][1] = list.get(i)[1];
        }

        return ans;
    }
}
