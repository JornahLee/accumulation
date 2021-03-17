package com.jornah.algorithm.sort;

import java.util.Arrays;

public class BubbleSort {

    public static void main(String[] args) {
        int[] a = {4, 55, 12, 51, 5, 1};
        sort(a);
        Arrays.stream(a).forEach(System.out::println);

    }

    public static void sort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            // -1 是因为 总是比较j与j+1， 不 -1 会越界
            // -i 是因为 不需要与冒过泡的元素比较，因为之前都比较过了
            for (int j = i; j < nums.length - 1 - i; j++) {
                if (nums[j] > nums[j + 1]) {
                    Util.exchange(nums, j, j + 1);
                }
            }
        }

    }
}
