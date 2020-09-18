package com.jornah.javabase;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * 优雅使用lambda
 */
public class LambdaDemo {
    public static void main(String[] args) {
        List<StringBuilder> sbs = Arrays.asList(new StringBuilder("1"), new StringBuilder("1"), new StringBuilder("3"), new StringBuilder("33"), new StringBuilder("12"), new StringBuilder("9"));
        sbs.stream().peek(str -> {
            if (str.length() > 1) {
                str.setLength(0);
            }
        }).forEach(System.out::println);

        Optional<StringBuilder> sb = Optional.of(new StringBuilder());
        // sb.ifPresent(consumer);  存在则消耗
        // sb.orElse(T)     存在返回当前
        // sb.orElseThrow(exception)  不存在抛异常
        // sb.orElseGet(supplier)   不存在，则通过 supplier获取
    }
    // lambda 的

    public void testFlatMap(){
        List<String> strings = Arrays.asList("woshi shei", "woshi nidie", "woshi niba");
        List<String> collect = strings.stream().flatMap(str -> Stream.of(str.split(" "))).collect(Collectors.toList());

    }
}
