package com.jornah.algorithm.sort;

import java.util.Arrays;

public class ShellSort {
    public static void main(String[] args) {
        int[] a = {4, 55, 12, 51, 5};
        sort(a);
        Arrays.stream(a).forEach(System.out::println);
    }

    public static void sort(int[] nums) {
        // 分组的直接插入排序
        int h = nums.length / 2;
        while (h > 0) {
            System.out.println("step:" + h);
            for (int t = 0; t < h; t++) {
                System.out.println("group:" + t);
                for (int i = t + h; i < nums.length; i += h) {
                    System.out.println("--licg---      : " + nums[i] + "    -----");
                    // 在这里直接进行 插入排序 即可
                    // 从第二个元素开始
                    int j = i - h;
                    int tempValue = nums[i];
                    while (j >= 0 && tempValue <= nums[j]) {
                        nums[j + h] = nums[j];
                        j -= h;
                    }
                    nums[j + h] = tempValue;
                }
            }
            System.out.println("-----");
            h /= 2;
        }
    }

}
