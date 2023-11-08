package medium;

/**
 * 621. 任务调度器
 * https://leetcode.cn/problems/task-scheduler
 *
 * 贪心
 */
public class Problem_0621_LeastInterval {

    public int leastInterval(char[] tasks, int n) {
        int maxCount = 0, maxNum = 0;
        // 字母都为大写字母，Z的ASCII码是90，也可以定义为256，包含所有字符
        int[] counts = new int[91];

        for (char task : tasks) {
            counts[task]++;
            maxCount = Math.max(maxCount, counts[task]);
        }

        for (int count : counts) {
            if (count == maxCount) {
                maxNum++;
            }
        }

        // 最大次数 - 1 表示 分成几块
        // n + 1 表示 一块需要多大面积
        // maxNum 表示 最后一节的长度
        // 这两个值比较大小 因为如果前面所有的块没有填满，(maxCount - 1) * (n + 1) + maxNum 是最少需要的时间
        // 如果前面都填满了，说明没有任何等待期，tasks.length 任务总数就是最少需要的时间
        return Math.max((maxCount - 1) * (n + 1) + maxNum, tasks.length);
    }
}
