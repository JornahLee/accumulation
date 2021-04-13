package com.jornah.algorithm.sort;

import java.util.Arrays;

// 归并排序
public class MergeSort {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 10, 55, 100};
        GBSort(arr,0,arr.length-1);
        Arrays.stream(arr).forEach(System.out::println);
    }

    //归并排序的连接函数
    public static void merge(int[] para, int start, int mid, int end) {
        int[] temp = new int[end + 1];
        int i = 0, j = mid + 1, t = 0;
        while (i <= mid && j <= end) {
            if (para[i] < para[j]) {
                temp[t++] = para[i++];
            } else {
                temp[t++] = para[j++];
            }
        }
        while (i <= mid) {
            temp[t++] = para[i++];
        }
        while (j <= end) {
            temp[t++] = para[j++];
        }
        System.arraycopy(temp, 0, para, 0, temp.length);

    }

    //归并排序
    public static void GBSort(int[] para, int start, int end) {

        if (start < end) {
            int mid = (start + end) / 2;
            GBSort(para, start, mid);
            GBSort(para, mid + 1, end);
            merge(para, start, mid, end);

        } else {

            //不需要elese
            //因为递归的分的最小粒子： 就是 拍左 左只有一个元素 ，排右 右只有一个元素， 然后连接，完成两个元素的排序。
        }

        //不可以递归
        //分治，再回归。 如何回归？什么时候回归？
        //将二叉树转换为平衡二叉树？ 1.二叉树转数组，数组创建平衡二叉树


    }
}
