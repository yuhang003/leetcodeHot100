package medium;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 739. 每日温度
 * https://leetcode.cn/problems/daily-temperatures
 */
public class Problem_0739_DailyTemperatures {

    // 单调栈
    public int[] dailyTemperatures(int[] temperatures) {
        if (temperatures == null || temperatures.length == 0) return new int[0];

        Deque<Integer> stack = new LinkedList<>();
        int N = temperatures.length;
        int[] ans = new int[N];

        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                int index = stack.pop();
                ans[index] = i - index;
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            ans[stack.pop()] = 0;
        }

        return ans;
    }

    // 使用数组模拟单调栈
    public int[] dailyTemperatures2(int[] temperatures) {
        if (temperatures == null || temperatures.length == 0) return new int[0];

        int N = temperatures.length;
        int[] ans = new int[N];
        int[] stack = new int[N];

        int index =  -1;
        for (int i = 0; i < N; i++) {
            while (index >= 0 && temperatures[stack[index]] < temperatures[i]) {
                int preIndex = stack[index--];
                ans[preIndex] = i - preIndex;
            }

            stack[++index] = i;
        }

        while (index >= 0) {
            ans[stack[index--]] = 0;
        }

        return ans;
    }
}
