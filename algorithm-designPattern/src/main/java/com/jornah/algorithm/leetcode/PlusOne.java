package com.jornah.algorithm.leetcode;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author licong
 * @date 2021/10/9 00:39
 */
public class PlusOne {
    public static void main(String[] args) {
        int[] arr = {1, 1, 9, 9, 9};
        Arrays.stream(plusOne(arr)).forEach(System.out::print);

    }

    private static int[] plusOne(int[] arr) {
        int jw = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            int plusRes = 0;
            if (i == arr.length - 1) {
                plusRes += 1;
            }
            plusRes += arr[i] + jw;
            if (plusRes >= 10) {
                arr[i] = plusRes - 10;
                jw = 1;
            } else {
                jw = 0;
                arr[i] = plusRes;
            }
        }
        if (jw == 1) {
            int[] newArr = new int[arr.length + 1];
            newArr[0] = 1;
            for (int i = 1, j = 0; i < newArr.length; i++, j++) {
                newArr[i] = arr[j];
            }
            return newArr;
        }
        return arr;
    }

}
