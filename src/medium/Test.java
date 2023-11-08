package medium;

import entity.TreeNode;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Stream;

/**
 * 测试volatile修饰的变量，在synchronized代码块中修改，其他线程能看到值吗？
 */
public class Test {
//    public static int count = 1;
//    public static AtomicInteger count = new AtomicInteger(1);
    public static int count = 1;
    public static CountDownLatch latch = new CountDownLatch(1);

    private String a;

    public static void main(String[] args) {
//        Map<String, Object> map = new HashMap<>();
////        ArrayList<String> objects = new ArrayList<>();
////        objects.add("lihang");
////        map.put("a", objects);
//
//        Collection<String> collection = (Collection<String>) map.get("a");
//
//        System.out.println(collection);
//
//        Optional.ofNullable(collection).ifPresent(coll -> {
//            System.out.println(coll);
//        });

//        int[] arr = new int[0];
//        System.out.println(arr.toString());
//        Map<String, Object> map = new HashMap<>();
//        map.put("a", "a");
//        System.out.println(map.toString());

        int[] arr = {22, 222, 76, 26, 87, 99};
        System.out.println(FindSecMax(arr));


        int pos = findSubStrPos("strstr1str2str3", "str2");
        System.out.println(pos);
    }

    public List<List<Integer>> findMatrix(int[] nums) {
        List<Set<Integer>> list = new ArrayList<>();
        list.add(new HashSet<>());

        for (int num : nums) {
            boolean flag = false;
            for (Set<Integer> set : list) {
                if (set.add(num)) {
                    flag = true;
                    break;
                }
            }

            if (!flag) {
                Set<Integer> temp = new HashSet<>();
                temp.add(num);
                list.add(temp);
            }
        }

        List<List<Integer>> ans = new ArrayList<>();
        for (Set<Integer> set : list) {
            ans.add(new ArrayList<>(set));
        }

        return ans;
    }


    public static int FindSecMax(int[] arry) {
        int count = arry.length;
        int maxNumber = arry[0];
        int secMax = Integer.MIN_VALUE;
        for (int i = 0; i < count; i++) {
            if (arry[i] > maxNumber) {
                secMax = maxNumber;
                maxNumber = arry[i];

            } else {
                if (arry[i] > secMax) {
                    secMax = arry[i];

                }
            }
        }
        return secMax;
    }


    public static int findSubStrPos(String str, String subStr) {
        if (subStr.length() == 0) return 0;
        if (str.length() == 0) return -1;
        int subLen = subStr.length();

        for (int i = 0; i < str.length() - subLen + 1; i++) {
            if (str.substring(i, i + subLen).equals(subStr)) {
                return i;
            }
        }
        return -1;
    }

}
