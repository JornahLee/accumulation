package com.jornah.algorithm.leetcode;

/**
 * @author licong
 * @date 2021/10/9 00:39
 */
public class AppearOnce {
    public static void main(String[] args) {
        int[] nums = {1,1,1,1,2};
        System.out.println(0^0);
        int ret=0;
        for (int num : nums) {
            ret^=num;
        }
        System.out.println(ret);
//        System.out.println(xx2(new int[]{1, 2, 3, 4, 5}));
//        System.out.println(xx2(new int[]{7,1,5,3,6,4}));
        // 不行呀， 那用set呢，记录所有 add false的， 然后把set中的 false 一起remove了， 不用记录浪费空间，如果add fale，说明存在了，直接清除，
        // 先排序 消消乐

    }

}
