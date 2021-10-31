package com.jornah.algorithm.leetcode;

import jdk.nashorn.internal.ir.IfNode;

import java.util.Arrays;

/**
 * @author licong
 * @date 2021/10/11 23:15
 */
//是否存在相同元素
public class DuplicateYs {
    public static void main(String[] args) {
//        int[] arr = new int[]{4, 2, 3, 0, 6, 8, 1};
        int[] arr = new int[]{-24500, -24499, -24498, -24497, -24496, -24495, -24494, -24493, -24492, -24491, -24490, -24489, -24488, -24487, -24486, -24485, -24484, -24483, -24482, -24481, -24480, -24479, -24478, -24477, -24476, -24475, -24474, -24473, -24472, -24471, -24470, -24469, -24468, -24467, -24466, -24465, -24464, -24463, -24462, -24461, -24460, -24459, -24458, -24457, -24456, -24455, -24454, -24453, -24452, -24451, -24450, -24449, -24448, -24447, -24446, -24445, -24444, -24443, -24442, -24441, -24440, -24439, -24438, -24437, -24436, -24435, -24434, -24433, -24432, -24431, -24430, -24429, -24428, -24427, -24426, -24425, -24424, -24423, -24422, -24421, -24420, -24419, -24418, -24417, -24416, -24415, -24414, -24413, -24412, -24411, -24410, -24409, -24408, -24407, -24406, -24405, -24404, -24403, -24402, -24401, -24400, -24399, -24398, -24397, -24396, -24395, -24394, -24393, -24392, -24391, -24390, -24389, -24388, -24387, -24386, -24385, -24384, -24383, -24382, -24381, -24380, -24379, -24378, -24377, -24376, -24375, -24374, -24373, -24372, -24371, -24370, -24369, -24368, -24367, -24366, -24365, -24364, -24363, -24362, -24361, -24360, -24359, -2435};
        System.out.println(arr.length);
        System.out.println("---");
        sort(arr);
        Arrays.stream(arr).forEach(System.out::println);
        boolean flag = hasDumplicate2(arr);
        // 使用hashSet， 如果size变了，就说明有重复
        // 排序之后，再遍历数组，判断相邻元素是否相等
        System.out.println(flag);
    }

    private static boolean hasDumplicate2(int[] arr) {
        Arrays.sort(arr);
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] == arr[i + 1]) {
                return true;
            }
        }
        return false;
    }

    private static boolean hasDumplicate(int[] arr) {
        // 最简单解法， 双指针
        // fk leetcode 基本上使用双指针的解法， 都会超时 恶心
        boolean flag = false;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (i == j) {
                    continue;
                }
                if (arr[i] == arr[j]) {
                    flag = true;
                }
            }
        }
        return flag;
    }

    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }

            }
        }
    }

    public static void swap(int arr[], int fromIndex, int toIndex) {
        int temp;
        temp = arr[fromIndex];
        arr[fromIndex] = arr[toIndex];
        arr[toIndex] = temp;
    }

    public void quickSort(int[] arr) {
//        merge 归并
        // 直接插入
//        希尔排序
//        直接交换
//        冒泡
    }

    public void mergeSort(int[] arr) {
        doMergeSort(arr, 0, arr.length - 1);
    }

    public void doMergeSort(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }
        // zuo
        doMergeSort(arr, start, (start + end) / 2);
        doMergeSort(arr, (start + end) / 2 + 1, end);
        // merge
        // 写到这里 我不禁陷入了沉思，左边有序，右边有序， 为啥需要连接起来呢 2333
        // 等理解了再重写吧

    }

}
