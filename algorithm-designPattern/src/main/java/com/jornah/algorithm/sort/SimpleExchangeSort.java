package com.jornah.algorithm.sort;

import java.util.Arrays;

public class SimpleExchangeSort {

    public static void main(String[] args) {
        int[] a = {4, 55, 12, 51, 5, 1};
        sort(a);
        Arrays.stream(a).forEach(System.out::println);
    }

    public static void sort(int[] nums) {
        // i from 0 to max， 挨个填充剩余部分的最小值
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < nums[i]) {
                    Util.exchange(nums, i, j);
                }
            }
        }
    }

}
