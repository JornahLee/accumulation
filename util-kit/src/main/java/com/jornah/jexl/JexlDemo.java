package com.jornah.jexl;

import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.MapContext;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author licong
 * @date 2021/6/23 15:00
 */
public class JexlDemo {
    public static void main(String[] args) {
        // System.out.println(BigDecimal.valueOf(10.111111).multiply(BigDecimal.valueOf(100)).longValue());
        test1();
        // System.out.println(BigDecimal.valueOf(12102.122245656).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue());
    }

    private static void test1() {
        // 执行字符串表达式：(k-(x-y)*0.1)，进行计算
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("kf1s1", 10);
        map.put("x", 2);
        map.put("y", 4);
        String formula = "kf1s1*100 ";
        Object result = convertToCode(formula,map);
        System.out.println(result);
    }

    /**
     * java将字符串转换成可执行代码 工具类
     *
     * @param jexlExp
     * @param map
     * @return
     */
    private static Object convertToCode(String jexlExp, Map<String, Object> map) {
        JexlEngine jexl = new JexlEngine();
        Expression expression = jexl.createExpression(jexlExp);
        JexlContext jc = new MapContext();
        for (String key : map.keySet()) {
            jc.set(key, map.get(key));
        }
        if (null == expression.evaluate(jc)) {
            return "";
        }
        return expression.evaluate(jc);
    }
}
