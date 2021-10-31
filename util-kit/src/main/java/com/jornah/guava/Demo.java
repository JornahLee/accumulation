package com.jornah.guava;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Demo {

    public static void main(String[] args) throws IOException {
        int[][] ints = new int[][]{{1, 2, 3}, {10, 20, 30}};
        // 1 10
        // 2 20
        // 3 30
        ints[1][1]=100;
        for (int[] anInt : ints) {
            for (int i : anInt) {
                System.out.print(" " + i);
            }
            System.out.println();
        }
        System.out.println(ints[0][2]);
        System.out.println(ints[1][2]);
//        Lists.as
//        Lists.newArrayList()
//        int result = ComparisonChain.start().compare(1, 2).
//        Arrays.copy

    }

    private static void test() {
//        Computer.builder().
    }

    private static void test1() {
        Preconditions.checkArgument(true);
        System.out.println(Objects.hashCode(new Object()));
        System.out.println(Objects.toString(new Object()));
        int[] arr = {1, 2, 32, 32, 32, 32, 3, 2};
        System.out.println(Arrays.toString(arr));
        List<Integer> li = Arrays.asList(1, 2, 3);
        ArrayList<Object> objects = Lists.newArrayList(1, 2, 3);
        Arrays.stream(arr).forEach(System.out::println);
        int[] res = Arrays.copyOf(arr, arr.length);
//        Arrays.copyOfRange(arr)
        System.out.println(Arrays.toString(res));
    }

    //    static
    static class Computer {
        private String cpu;
        private String gpu;
        private String memory;

        static ComputerBuilder builder() {
            return new ComputerBuilder();
        }

        static class ComputerBuilder {
            private String cpu;
            private String gpu;
            private String memory;


            public ComputerBuilder Cpu(String cpu) {
                this.cpu = cpu;
                return this;
            }

            public ComputerBuilder Gpu(String gpu) {
                this.gpu = gpu;
                return this;
            }

            public ComputerBuilder Memory(String memory) {
                this.memory = memory;
                return this;
            }
        }
    }
}
