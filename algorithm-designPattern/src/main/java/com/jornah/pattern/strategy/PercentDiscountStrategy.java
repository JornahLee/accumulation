package com.jornah.pattern.strategy;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author licong
 * @date 2021/8/26 20:41
 */
public class PercentDiscountStrategy implements DiscountStrategy<Integer> {


    @Override
    public BigDecimal discount(Integer percent, BigDecimal source) {
        return source.multiply(BigDecimal.valueOf(percent))
                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
    }
}
