package com.jornah.algorithm.leetcode;

import java.util.Arrays;

//88. 合并两个有序数组
public class Q88 {

    public static void main(String[] args) {
        int[] arr1 = new int[]{0};
        int[] arr2 = new int[]{1};
        merge2_version2(arr1, 0, arr2, 1);
        Arrays.stream(arr1).forEach(System.out::println);
    }

    // 合并到某一个数组
    private static void merge2_version1(int[] arr1, int len1, int[] arr2, int len2) {
        // 此处直接使用 直接插入排序，即可把其中一个数组合并到另一个
        int i = len1 - 1;
        int j = len2 - 1;
        int m = len1 + len2 - 1;
        while (j >= 0 && i >= 0) {
            if (arr2[j] > arr1[i]) {
                arr1[m--] = arr2[j--];
            } else {
                arr1[m--] = arr1[i--];
            }
        }
        // 这里的两个循环可以合并
        while (j >= 0) {
            arr1[j] = arr2[j--];
        }
    }

    public static void merge2_version2(int[] arr1, int len1, int[] arr2, int len2) {
        // 此处直接使用 直接插入排序，即可把其中一个数组合并到另一个
        int i = len1 - 1;
        int j = len2 - 1;
        int m = len1 + len2 - 1;
        // 思考： 合并两个循环，是否有迹可循？
        while (j >= 0) {
            arr1[m--] = (i >= 0 && arr1[i] > arr2[j]) ? arr1[i--] : arr2[j--];
        }
        // 等价于如下代码
        // while (j >= 0 && i >= 0) {
        //     if (arr2[j] > arr1[i]) {
        //         arr1[m--] = arr2[j--];
        //     } else {
        //         arr1[m--] = arr1[i--];
        //     }
        // }
    }

    // 合并到第三个数组
    private static void merge1() {
        int[] arr1 = new int[]{1, 5, 11, 66, 123};
        int[] arr2 = new int[]{7, 42, 88, 411, 1111};
        int[] res = new int[arr1.length + arr2.length];
        int i = 0, j = 0, r = 0;
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] > arr2[j]) {
                res[r++] = arr2[j++];
            } else {
                res[r++] = arr1[i++];
            }
        }
        while (i < arr1.length) {
            res[r++] = arr1[i++];
        }
        while (j < arr2.length) {
            res[r++] = arr2[j++];
        }
        Arrays.stream(res).forEach(System.out::println);
    }
}

