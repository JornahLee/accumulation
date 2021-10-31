package com.jornah.algorithm.leetcode;

/**
 * @author licong
 * @date 2021/10/11 23:15
 */
public class Xiu {
    public static void main(String[] args) {
        int[] arr = new int[]{4, 2, 3, 1, 6, 8, 1};
        movPointer(arr);
    }

    private static void movPointer(int[] arr) {
        int i = 0;
        // 将i平移指向到 >= 3的元素， 判断之后再停下，因此循环结束时，循环体外，指针指向目的题
        // 那循环体内的指针到底指向了哪些元素呢？ 循环体内只指向了 [起点, 目的地-1] 区间的所有元素
        int temp = 5;
        while (i < arr.length && arr[i] < temp) {
            System.out.print(arr[i] + ",");
            i++;
        }
        for (int j = 0; j < arr.length && arr[i] < temp; j++) {

        }

        System.out.println();
        System.out.println("res:" + arr[i]);
    }

    private static void boundary(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            // 比较相邻两个值的
            if (arr[i] > arr[i + 1]) {

                // i< x
                // i+1 <x
                // i<x-1
            }
        }
        for (int i = 1; i < arr.length; i++) {
            // 比较相邻两个值的
            if (arr[i - 1] > arr[i]) {
                // i< x
                // i+1 <x
                // i<x-1
            }
        }
    }
}
