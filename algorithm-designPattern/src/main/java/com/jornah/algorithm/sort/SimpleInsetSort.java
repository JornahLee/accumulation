package com.jornah.algorithm.sort;

import java.util.Arrays;

public class SimpleInsetSort {

    public static void main(String[] args) {
        int[] a = {4, 55, 12, 51, 5, 1};
        sort(a);
        Arrays.stream(a).forEach(System.out::println);

    }

    public static void sort(int[] nums) {
        // 只有一个元素，则有序
        int j, tempValue;
        for (int i = 1; i < nums.length; i++) {
            j = i - 1;
            tempValue = nums[i];
            while (j >= 0 && tempValue <= nums[j]) {
                nums[j + 1] = nums[j];
                j--;
            }
            nums[j + 1] = tempValue;
        }
    }
    // 总结，
    // 满足条件下的指针移动，while(符合指针边界 &&condition) i--;
    // 当前指针符合condition,则指针会后移动到下一个单位

    // 双指针同时遍历
    // while (i < nums1.length || j < nums2.length) {
    //     //这个技巧经常用到，当一个已经遍历结束的话，我们将其赋值为 0
    //     String num1 = i < nums1.length ? nums1[i] : "0";
    //     String num2 = j < nums2.length ? nums2[j] : "0";
    // }
}
