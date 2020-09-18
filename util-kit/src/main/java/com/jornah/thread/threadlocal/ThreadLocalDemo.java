package com.jornah.thread.threadlocal;

import org.apache.commons.lang3.RandomUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * threadLocal 其实应该叫 ThreadLocalVariable，是一个本地线程变量.
 * 作用： 对线程共享变量进行包装，使该变量线程隔离，每一个线程对该变量的操作都不会影响其他线程
 * 应用举例1：
 * SpringMvc RequestContextHolder.currentRequestAttributes 就是通过该方式实现
 * servlet接受一次请求会开启一个线程，所以request信息与线程绑定，维护了一个针对当前请求的一个全局变量。
 * 并发请求的时候，所有请求都可以访问currentRequestAttributes， 但是都是获取各自的信息，以此达到隔离的效果
 * 应用举例2：
 * hibernate事务：
 * 开启一个trans时，会将其保存到threadlocal，多个方法调用时都从threadlocal获取，这样可以使不同方法在同一个事务中
 * 且并发查询的时候，各个线程-事务互不影响
 *
 */
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
