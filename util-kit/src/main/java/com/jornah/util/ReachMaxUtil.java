package com.jornah.util;

public class ReachMaxUtil {
    public static void main(String[] args) {
        boolean res = checkIsReachMax(11,11,false);
        boolean res1 = checkIsReachMax(11,11,true);
        System.out.println(res);
        System.out.println(res1);
    }
    // containSelf 是否算上自己
    private static boolean checkIsReachMax(int current, int maxCount ,boolean currentContainsSelf) {
        if (maxCount > current) {
            return false;
        }
        if (currentContainsSelf) {
            // current中包含了自己
            return current - 1 >= maxCount;
        } else {
            // current中没包含了自己
            return current >= maxCount;
        }
    }
}
