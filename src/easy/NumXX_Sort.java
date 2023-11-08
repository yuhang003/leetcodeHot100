package easy;

import java.util.Arrays;

public class NumXX_Sort {

    public static void main(String[] args) {
        int[] arr = {29, 10, 14, 37, 14};
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
        arr = new int[]{29, 10, 14, 37, 14};
        insertionSort(arr);
        System.out.println(Arrays.toString(arr));
        arr = new int[]{29, 10, 14, 37, 14};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
        arr = new int[]{29, 10, 14, 37, 14};
        mergeSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    // 冒泡算法
    public static void bubbleSort(int[] arr) {
        int length = arr.length;
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp;
                    temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    // 插入排序
    public static void insertionSort(int[] arr) {
        int length = arr.length;
        for (int i = 1; i < length; i++) {
            int temp = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > temp) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = temp;
        }
    }

    // 快速排序
    public static void quickSort(int[] arr, int start, int end) {
        if (start >= end) return;

        int[] equalE = partition(arr, start, end);
        quickSort(arr, start, equalE[0] - 1);
        quickSort(arr, equalE[1] + 1, end);
    }

    private static int[] partition(int[] nums, int L, int R) {
        int val = nums[R];
        int less = L - 1, more = R + 1, index = L;

        while (index < more) {
            if (nums[index] == val) {
                index++;
            } else if (nums[index] < val) {
                less++;
                swap(nums, less, index);
                index++;
            } else {
                more--;
                swap(nums, index, more);
            }
        }

        swap(nums, more, R);
        return new int[]{less + 1, more};
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // 归并排序
    public static void mergeSort(int[] nums, int L, int R) {
        if (L == R) return;

        int mid = L + (R - L) / 2;
        mergeSort(nums, L, mid);
        mergeSort(nums, mid + 1, R);
        merge(nums, L, mid, R);
    }

    private static void merge(int[] arr, int L, int M, int R) {
        int[] help = new int[R - L + 1];
        int i = 0;
        int p1 = L, p2 = M + 1;

        while (p1 <= M && p2 <= R) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }

        while (p1 <= M) {
            help[i++] = arr[p1++];
        }

        while (p2 <= R) {
            help[i++] = arr[p2++];
        }

        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
    }


    public static void sort(int[] arrays, int left, int right) {
        if (left > right) {
            return;
        }
        int l = left;
        int r = right;
        int pivot = arrays[left];
        int temp = 0;
        while (l < r) {
            while (pivot <= arrays[r] && l < r) {
                r--;
            }
            while (pivot >= arrays[l] && l < r) {
                l++;
            }
            if (l <= r) {
                temp = arrays[r];
                arrays[r] = arrays[l];
                arrays[l] = temp;
            }
        }
        arrays[left] = arrays[l];
        arrays[l] = pivot;
        sort(arrays, left, l - 1);
        sort(arrays, l + 1, right);
    }
}
