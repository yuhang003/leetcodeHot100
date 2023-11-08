package medium;

import java.util.*;

/**
 * 207. 课程表
 * https://leetcode.cn/problems/course-schedule
 *
 * 拓扑排序
 */
public class Problem_0207_CanFinish {

    class Course {
        public int name;
        public int in;
        public List<Course> nexts;

        public Course(int n) {
            name = n;
            in = 0;
            nexts = new ArrayList<>();
        }
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites == null || prerequisites.length == 0) return true;

        // 一个编号，对应一个课程实例
        Map<Integer, Course> courseMap = new HashMap<>();
        for (int[] prerequisite : prerequisites) {
            int to = prerequisite[0];
            int from = prerequisite[1];

            if (!courseMap.containsKey(to)) {
                courseMap.put(to, new Course(to));
            }
            if (!courseMap.containsKey(from)) {
                courseMap.put(from, new Course(from));
            }

            Course t = courseMap.get(to);
            Course f = courseMap.get(from);
            f.nexts.add(t);
            t.in++;
        }

        int needPrerequisiteNums = courseMap.size();
        int count = 0;
        Queue<Course> zeroInQueue = new LinkedList<>();
        for (Course course : courseMap.values()) {
            if (course.in == 0) {
                zeroInQueue.add(course);
            }
        }

        while (!zeroInQueue.isEmpty()) {
            Course course = zeroInQueue.poll();
            count++;
            for (Course next : course.nexts) {
                if (--next.in == 0) {
                    zeroInQueue.add(next);
                }
            }
        }

        return count == needPrerequisiteNums;
    }

    public static void main(String[] args) {
        int[][] prerequisites = {{0,10},{3,18},{5,5},{6,11},{11,14},{13,1},{15,1},{17,4}};
        int numCourses = 20;
        System.out.println(canFinish2(numCourses, prerequisites));
    }

    public static boolean canFinish2(int numCourses, int[][] prerequisites) {
        if (prerequisites == null || prerequisites.length == 0) return true;

        // key:某一个课程， value:学完 key课程后，可以学习的课程，都放在list中
        Map<Integer, List<Integer>> nexts = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            nexts.put(i, new ArrayList<>());
        }
        // 入度个数，例如：[3, 1]学完了1才可以学3，即 1 -> 3, 对于3来说，就有了一个入度，in[3] = 1
        int[] in = new int[numCourses];
        for (int[] prerequisite : prerequisites) {
            int to = prerequisite[0];
            int from = prerequisite[1];
            // 学完了1 就可以学 3
            nexts.get(from).add(to);
            in[to]++;
        }

        // 用数组模拟队列
        int[] zero = new int[numCourses];
        // 该队列有效范围是[l, r)
        // 入队列的数，放在 r 位置，r++
        // 出队列的数，从 l 位置取，l++
        // l == r，队列无元素，l < r，队列有元素
        int l = 0, r = 0;
        // 先将最初入度为 0 的课程存进队列
        for (int i = 0; i < in.length; i++) {
            if (in[i] == 0) {
                zero[r++] = i;
            }
        }

        int count = 0;
        while (l < r) {
            count++;
            for (int next : nexts.get(zero[l++])) {
                if (--in[next] == 0) {
                    zero[r++] = next;
                }
            }
        }

        return count == numCourses;
    }
}
