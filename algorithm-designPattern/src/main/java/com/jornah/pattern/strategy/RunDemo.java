package com.jornah.pattern.strategy;

import java.math.BigDecimal;

/**
 * @author licong
 * @date 2021/8/26 20:41
 */
public class RunDemo {
    public static void main(String[] args) {
        // get product discount info
        // 如果输入要跟着变的话 那代码就要跟着变？
        int userInput;
        //
        Context<Integer> ctx = new Context<>(new PercentDiscountStrategy());
        BigDecimal exec = ctx.exec(10, BigDecimal.valueOf(100));
        System.out.println(exec);
//        我心中的的策略模式
        // 值不同 类型相同的输入？
        // 做不同的处理
        // 返回类型相同 值不同的结果？
        // 职责单一原则， 如果想要做的特别全， 职责就不单一了，啥都能进，啥都能出

        // 我好像把适配器模式 和 策略模式搞错了

    }
}
