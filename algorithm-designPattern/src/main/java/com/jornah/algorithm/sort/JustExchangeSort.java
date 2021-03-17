package com.jornah.algorithm.sort;

import java.util.Arrays;

public class JustExchangeSort {

    public static void main(String[] args) {
        int[] a = {4, 55, 12, 51, 5, 1};
        sort(a);
        Arrays.stream(a).forEach(System.out::println);

    }

    public static void sort(int[] nums) {
        // 下标i的元素 from 0 to nums.length， 逐个成为max或min的过程
        // 第一波 nums[0] 成为最小值
        // 第二波 nums[1] 成为剩下中的最小值
        // ...
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j]) {
                    Util.exchange(nums, i, j);
                }
            }
        }
    }
}
