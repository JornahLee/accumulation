package com.jornah.algorithm;

public class Recursive {
    /**
     *
     *拆解复杂问题 使其成为 若干相同单元
     *
     * 递归核心三步：
     * 第一步： 确定递归的功能，找到复杂问题基本单元
     * 第二步： 确定递归返回条件
     * 第三部： 递归 表达式
     *
     * 1.举例： 设 f(int n)为求n的阶乘
     * 2.如果 n=1 时, f(1)=1
     * 3.return n*f(n-1);
     *      其实本质是找等价拆解表达式 ： f(n)=n*f(n-1); 递归核心难点就是找 等价拆解表达式和返回条件
     * f(n)=n*f(n-1)                （一式)
     *  f(n-1)=(n-1)*f(n-1-1)        (二式)
     *  f(n-1-1)=(n-1-1)*f(n-1-1-1)  (三式)
     *  将二三式 代入 一式 得：         （代入过程  对应 函数递归调用过程）
     * f(n)= n*(n-1)*(n-1-1)*f(n-1-1-1)
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(fun(5));
    }
    public static int fun(int n) {
        if(n==1) {
            return 1;
        }
        return n*fun(n-1);
    }
}
