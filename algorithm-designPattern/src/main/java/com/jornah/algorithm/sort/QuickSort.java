package com.jornah.algorithm.sort;

import java.util.Arrays;

import static com.jornah.algorithm.sort.Util.exchange;

public class QuickSort {

    public static void main(String[] args) {
        int[] a = {111,15,5,4, 55, 12, 51, 5, 1,-100,-24,111};
        sort(a);
		Arrays.stream(a).forEach(System.out::println);
    }

    public static void sort(int[] nums) {
        // 分治 递归
        //假如数组只有一个元素，则有序
        quickSort(nums, 0, nums.length - 1);

    }

    public static void quickSort(int[] nums, int startIndex, int endIndex) {
        // 数组元素数量<=1,则数组为有序的
        if (endIndex - startIndex <= 1) {
            return;
        }
        //
        int tempIndex = startIndex;
        int temp = nums[startIndex];
        // 印象中采用双指针，头尾各一个
        //
        int i = startIndex, j = endIndex;
        while (i < j) {
        	// 哪边哨兵先移动，取决于初始temp变量的取值位置，如果取startIndex,则右哨兵先动，反之亦然
        	// 右边哨兵移动
            while (nums[j] >= temp && i < j) {
                j--;
            }
            // 左边哨兵移动
            while (nums[i] <= temp && i < j) {
                i++;
            }
            // 此时：nums[i] >temp , nums[j]<temp ,交换!
            exchange(nums, i, j);
        }
        // 走到最后i,j相遇了，
        // i==j了
        exchange(nums, tempIndex, j);
        // temp交换
        quickSort(nums, startIndex, j);
        quickSort(nums, j + 1, endIndex);
    }
}
