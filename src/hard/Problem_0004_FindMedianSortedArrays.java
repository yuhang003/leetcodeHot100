package hard;

/**
 * 4. 寻找两个正序数组的中位数
 * https://leetcode.cn/problems/median-of-two-sorted-arrays
 *
 * 本题可拓展为 找到两个正序数组的第K小
 */
public class Problem_0004_FindMedianSortedArrays {

    public static void main(String[] args) {
        int[] nums1 = {1, 2};
        int[] nums2 = {3, 4};

        Problem_0004_FindMedianSortedArrays test = new Problem_0004_FindMedianSortedArrays();
        System.out.println(test.findMedianSortedArrays(nums1, nums2));
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int size = nums1.length + nums2.length;
        // 偶数 : true
        boolean even = (size & 1) == 0;

        // 两个数组都不为null
        if (nums1.length != 0 && nums2.length != 0) {
            // 如果总长度是偶数
            if (even) {
                return (findKthNum(nums1, nums2, size >> 1) + findKthNum(nums1, nums2, (size >> 1) + 1)) / 2d;
            } else {
                return findKthNum(nums1, nums2, (size >> 1) + 1);
            }
        } else if (nums1.length != 0) { // nums1不为null
            if (even) {
                return (nums1[(size >> 1) - 1] + nums1[size >> 1]) / 2d;
            } else {
                return nums1[size >> 1];
            }
        }

        // nums2不为null
        if (even) {
            return (nums2[(size >> 1) - 1] + nums2[size >> 1]) / 2d;
        } else {
            return nums2[size >> 1];
        }
    }

    // 两个数组可以不等长
    // 返回两个数组整体的第 kth小 的数
    public int findKthNum(int[] nums1, int[] nums2, int kth) {
        int l = nums1.length;
        int s = nums2.length;

        int[] longs = l >= s ? nums1 : nums2;
        int[] shorts = nums1 == longs ? nums2 : nums1;
        l = longs.length;
        s = shorts.length;

        // (1) kth <= s
        if (kth <= s) {
            return getUpMedian(longs, 0, kth - 1, shorts, 0, kth - 1);
        }

        // (2) s < kth <= l
        if (kth <= l) {
            if (longs[kth - s - 1] >= shorts[s - 1]) {
                return longs[kth - s - 1];
            }

            return getUpMedian(longs, kth - s, kth - 1, shorts, 0, s - 1);
        }

        // (3) kth > l
        if (longs[kth - s - 1] >= shorts[s - 1]) {
            return longs[kth - s - 1];
        }

        if (shorts[kth - l - 1] >= longs[l - 1]) {
            return shorts[kth - l - 1];
        }

        return getUpMedian(longs, kth - s, l - 1, shorts, kth - l, s - 1);
    }

    // A数组和B数组一定是等长的
    // 返回的是A和B数组整体的上中位数
    public int getUpMedian(int[] A, int start1, int end1, int[] B, int start2, int end2) {
        int mid1, mid2;
        while (start1 < end1) {
            mid1 = start1 + ((end1 - start1) >> 1);
            mid2 = start2 + ((end2 - start2) >> 1);
            if (A[mid1] == B[mid2]) return A[mid1];

            // 奇数长度
            if (((end1 - start1 + 1) & 1) == 1) {
                if (A[mid1] > B[mid2]) {
                    if (B[mid2] >= A[mid1 - 1]) return B[mid2];
                    end1 = mid1 - 1;
                    start2 = mid2 + 1;
                } else {
                    if (A[mid1] >= B[mid2 - 1]) return A[mid1];
                    start1 = mid1 + 1;
                    end2 = mid2 - 1;
                }
            } else {
                if (A[mid1] > B[mid2]) {
                    end1 = mid1;
                    start2 = mid2 + 1;
                } else {
                    start1 = mid1 + 1;
                    end2 = mid2;
                }
            }
        }

        return Math.min(A[start1], B[start2]);
    }
}
