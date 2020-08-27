package com.jornah.thread.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.String.join;

public class ThreadPoolDemo {
    private static AtomicInteger var = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> {
                for (int count = 0; count < 100; count++) {
                    var.incrementAndGet();
                }
            });
        }
        Thread.sleep(2000);
        System.out.println(join(" : ", "after", Thread.currentThread().getName(), var + ""));
        // 线程池停止接受任务，已在池中任务继续执行
        executorService.shutdown();

        // 同步等待线程池任务执行完毕
        executorService.awaitTermination(10, TimeUnit.SECONDS);
        // 主线程继续执行
    }

}
