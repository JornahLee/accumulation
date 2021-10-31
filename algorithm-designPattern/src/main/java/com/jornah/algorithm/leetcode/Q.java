package com.jornah.algorithm.leetcode;

/**
 * @author licong
 * @date 2021/10/9 00:39
 */
public class Q {
    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
//        System.out.println(xx2(new int[]{1, 2, 3, 4, 5}));
//        System.out.println(xx2(new int[]{7,1,5,3,6,4}));

    }

    // 双指针：常用解法，  直接交换排序，  快排，
    // 关键信息： 有序数组，
    // 有序数组特点； 相同元素一定相邻
    public int removeDuplicates(int[] nums) {
        int i = 0, j = 1;
        while (i < nums.length && j < nums.length) {
            if (nums[i] != nums[j]) {
                nums[++i] = nums[j];
            }
            j++;
        }
        return i + 1;
    }


    // 找出数组中的上升段 下降段
    public static int xx(int[] prices) {
        // 双指针找 ，一定有指针先驱动， 根据类型 选择循环类型？
        int j = 0;
        int count = 0;
        int buyIndex = -1;
        for (int i = 1; i < prices.length; i++, j++) {
            if (prices[j] <= prices[i]) {
                // 上升ing
                if (buyIndex < 0) {
                    buyIndex = j;
                }
            } else {
                // 遭遇下降段， 把j 到 i-1 之间算差值
//                并将j指针 与i重合， 此时即使需要连续下降，也不会出现重复出售
                if (buyIndex >= 0) {
                    count += (prices[i - 1] - prices[buyIndex]);
                    buyIndex = -1;
                }
            }
        }
        if (buyIndex >= 0) {
            count += (prices[prices.length - 1] - prices[buyIndex]);
        }
        return count;
    }

    // 线段连续下降，一定是相邻节点 f(x)< f(x+1)
    public static int maxProfit(int[] prices) {
        int j = 0;
        int count = 0;
        while (j < prices.length) {
            // 双指针遍历写法， 防 out of index
            // 下降段
            while (j < prices.length - 1 && prices[j] >= prices[j + 1]) j++;
            int min = j;
            // 上升段
            while (j < prices.length - 1 && prices[j] <= prices[j + 1]) j++;
            int max = j;
            count += prices[max] - prices[min];
            j++;
        }
        return count;
    }
}
