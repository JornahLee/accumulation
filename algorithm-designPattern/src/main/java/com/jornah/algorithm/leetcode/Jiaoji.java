package com.jornah.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author licong
 * @date 2021/10/9 00:39
 */
public class Jiaoji {
    public static void main(String[] args) {
        int[] arr1 = {1, 1, 1, 1, 2};
        int[] arr2 = {1, 2};
        int i = 0, j = 0;
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        ArrayList<Integer> list = new ArrayList<>();
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] == arr2[j]) {
                list.add(arr1[i]);
                i++;
                j++;
            } else if (arr1[i] < arr2[j]) {
                i++;
            } else {
                j++;
            }
        }

        int[] ret = new int[list.size()];
        for (int x = 0; x < ret.length; x++) {
            ret[x] = list.get(x);
        }
//        return ret;
    }

}
