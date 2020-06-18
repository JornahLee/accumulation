package com.jornah.thread.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.String.join;

public class ThreadPoolDemo {
    // private static int i = 0;
    private static AtomicInteger i=new AtomicInteger(0);
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            executorService.execute(new Task());
        }
        Thread.sleep(2000);
        System.out.println(join(" : ", "after",Thread.currentThread().getName(), i + ""));
        executorService.shutdown();
    }

    public static class Task implements Runnable {
        @Override
        public void run() {
            // for (int count = 0; count < 900; count++) {
            //     i++;
            // }
            for (int count = 0; count < 100; count++) {
                i.incrementAndGet();
            }
        }
        // while(i.get()<=50){
        //     i.incrementAndGet();
        // }
    }
}
