package com.jornah.pattern.strategy;

import java.math.BigDecimal;

/**
 * @author licong
 * @date 2021/8/26 20:38
 */
public interface DiscountStrategy<T> {
    /**
     * 不同策略 可能有不同的输入
     * 可能会有不同的输出吗？ 不会吧，如果输出不同的话，可以统一吗 好像也可以啊
     * @return
     */
    BigDecimal discount(T strategyInfo, BigDecimal sourcePrice);
}
