package com.jornah.thread.threadlocal;

import org.apache.commons.lang3.RandomUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadLocalDemo {

    private static final ThreadLocal<Integer> tlocal = new ThreadLocal<>();


    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
		for (int i2 = 0; i2 < 20; i2++) {
			executorService.execute(() -> {
				int i = RandomUtils.nextInt(1, 100);
				System.out.println("Thread: " + Thread.currentThread().getName() +"   random  i : " + i + "    -----");
				tlocal.set(i);
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("Thread: " + Thread.currentThread().getName() + "    ----- local.get()"+tlocal.get());
			});
		}
		TimeUnit.SECONDS.sleep(2);
		executorService.shutdown();
    }


}
