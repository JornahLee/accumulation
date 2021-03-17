package com.jornah.algorithm.sort;

public class Util {
    private Util() {
    }

    public static int[] exchange(int[] nums, int sourceIndex, int targetIndex) {
        int temp;
        temp = nums[sourceIndex];
        nums[sourceIndex] = nums[targetIndex];
        nums[targetIndex] = temp;
        return nums;
    }
}
