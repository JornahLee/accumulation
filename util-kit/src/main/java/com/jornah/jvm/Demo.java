package com.jornah.jvm;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Demo {
    // public static List<Object> list = Lists.newArrayList();
    // public static List<Object> list = new ArrayList<>();
    public static List<Object> list = new Vector<>();

    public static void main(String[] args) {
        // -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+PrintGCDateStamps -Xloggc:/var/logs/blog/gc.log
        // deadBlock();
        fun2();
    }


    public static void fun1() {
        while (true) {
            byte[] b = new byte[1024 * 1024];
            list.add(b);
            System.out.println("isRunning");
        }
    }
    public static void fun2() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 2; i++) {
            executorService.submit(() -> {
                while (true) {
                    TimeUnit.SECONDS.sleep(1);
                    byte[] b = new byte[1024 * 1024];
                    list.add(b);
                    System.out.println("isRunning");
                }
            });
        }
    }
    public static void deadBlock(){
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        executorService.submit(()->{
            synchronized (Demo.class){
                while (true) {
                    // System.out.println("keep lock");
                }
            }
        });
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 3; i++) {
            executorService.submit(()->{
                synchronized (Demo.class){
                    while (true) {
                        // System.out.println("keep lock");
                    }
                }
            });
        }

    }
}
