package com.jornah.algorithm.leetcode;

import java.util.Arrays;

/**
 * @author licong
 * @date 2021/10/11 23:15
 */
public class MovZero {
    public static void main(String[] args) {
//        输入: [0,1,0,3,12]
//        输出: [1,3,12,0,0]
        int[] nums = new int[]{0, 1, 2, 0, 3, 0, 0, 4, 5, 6, 0, 7, 8, 0, 9};
//        f1(nums);
//        f2(nums);
        int j=0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i]!=0){
                nums[j]=nums[i];
                j++;
            }
        }
       while( j<nums.length){
           nums[j]=0;
           j++;
       }
        Arrays.stream(nums).forEach(System.out::println);
    }

    private static void BINGO2(int[] nums) {
        int j=0;
        for (int i = 0; i < nums.length; i++) {
            // j  point 0
            if(nums[j]==0 && nums[i]!=0){
                // bingo
                nums[j]= nums[i];
                nums[i]=0;
                j++;
            }else if(nums[j]!=0){
                j++;
            }
        }
    }

    private static void bingo1(int[] nums) {
        int zeroIndex = nums.length;
        int i = 0;
        while (i < nums.length && i < zeroIndex) {
            if (nums[i] == 0) {
                int j = i;
                while (j < zeroIndex - 1) {
                    nums[j] = nums[j + 1];
                    j++;
                }
                zeroIndex--;
                nums[zeroIndex] = 0;
            } else {
                i++;
            }
        }
    }

    private static void f2(int[] nums) {
        int zeroIndex = nums.length - 1;
        for (int i = 0; i < nums.length && i < zeroIndex; i++) {
            if (nums[zeroIndex] == 0) {
                zeroIndex--;
            }
            if (nums[i] == 0) {
                int p = i;
                while (p < zeroIndex) {
                    nums[i] = nums[i + 1];
                    p++;
                }
                nums[p] = 0;
                zeroIndex--;
            }
        }
    }

    private static int[] f1(int[] nums) {
        int zeroIndex = nums.length - 1;
        for (int i = 0; i < nums.length && i < zeroIndex; i++) {
            if (nums[i] == 0) {
                if (nums[zeroIndex] == 0) {
                    zeroIndex--;
                }
                // exchange zeroIndex,i
                exchange(nums, i, zeroIndex);
                zeroIndex--;
            }
        }
        return nums;
    }

    public static void exchange(int[] arr, int from, int to) {
        int temp;
        temp = arr[from];
        arr[from] = arr[to];
        arr[to] = temp;

    }

}
