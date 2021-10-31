package com.jornah.pattern.strategy;

import java.math.BigDecimal;

/**
 * @author licong
 * @date 2021/8/26 20:40
 */
public class Context<T> {
    DiscountStrategy<T> strategy;

    public Context(DiscountStrategy<T> strategy) {
        this.strategy = strategy;
    }

    public BigDecimal exec(T typeInfo, BigDecimal s) {
        return strategy.discount(typeInfo, s);
    }
}
