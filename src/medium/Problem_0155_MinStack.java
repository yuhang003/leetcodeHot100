package medium;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 155. 最小栈
 * https://leetcode.cn/problems/min-stack
 *
 * 思路：使用两个栈，一个栈记录数据，一个栈记录最小值
 * 如果进来的数据，比最小值栈的栈顶元素小，直接入栈
 * 如果大，就将栈顶元素再次入栈，保证当前最小值一定在最小值栈的栈顶
 */
public class Problem_0155_MinStack {

    static class MinStack {
        Deque<Integer> dataStack;
        Deque<Integer> minStack;

        public MinStack() {
            dataStack = new LinkedList<>();
            minStack = new LinkedList<>();
        }

        public void push(int val) {
            dataStack.push(val);
            if (minStack.isEmpty()) {
                minStack.push(val);
            } else {
                minStack.push(Math.min(minStack.peek(), val));
            }
        }

        public void pop() {
            dataStack.pop();
            minStack.pop();
        }

        public int top() {
            return dataStack.isEmpty() ? -1 : dataStack.peek();
        }

        public int getMin() {
            return minStack.isEmpty() ? -1 : minStack.peek();
        }
    }
}
