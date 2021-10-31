package com.jornah.algorithm.leetcode;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author licong
 * @date 2021/10/11 23:15
 */
public class TwoSum {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 5, 6, 15, 2, 11, 556, 123};
        int target = 9;
        int[] ret = bingo2(nums, target);

        for (int i : ret) {
            System.out.println(nums[i]);
        }
//        Arrays.stream(ret).forEach(System.out::println);
    }

    private static int[] bingo1(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }
    private static int[] bingo2(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            Integer index = map.get(target - nums[i]);
            if (index!=null && index!=i) {
                return new int[]{i,map.get(target-nums[i])};
            }
        }
        return null;
    }
}
