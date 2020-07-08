package com.jornah.javabase.aboutextend;

import java.util.Arrays;
import java.util.List;

public class LambdaDemo {
    public static void main(String[] args) {
        List<StringBuilder> sbs = Arrays.asList(new StringBuilder("1"), new StringBuilder("1"), new StringBuilder("3"), new StringBuilder("33"), new StringBuilder("12"), new StringBuilder("9"));
        sbs.stream().peek(str->{
           if(str.length()>1){
               str.setLength(0);
           }
        }).forEach(System.out::println);
    }
}
