package com.jornah.guava;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

import java.util.*;

public class EnumDemo {
    enum Day {
        Monday(1), Tuesday, Wendsday, thrusday, friday, Satuarday, sunday;
        int index;

        Day(int index) {
            this.index = index;
        }

        Day() {
        }

        @Override
        public String toString() {
            return super.toString() + "{" +
                    "index=" + index +
                    '}';
        }
    }

    public static void main(String[] args) {
        test();
//        Day.valueOf()
//        Lists.as
//        Lists.newArrayList()
//        int result = ComparisonChain.start().compare(1, 2).
//        Arrays.copy

    }

    private static void test() {
//        Set<Day> days = EnumSet.of(Day.Monday, Day.friday);
//        EnumSet<Day> days = EnumSet.of(Day.Monday, Day.friday);
        for (Day value : Day.values()) {
            System.out.println(value);
        }

    }

}
