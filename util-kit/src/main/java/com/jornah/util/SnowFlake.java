package com.jornah.util;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * snowFlake算法.
 * 兼容JS最高支持53位二进制位数值型数据，缩短id
 * 时间从基于 毫秒 缩减到基于 秒
 */
public class SnowFlake {

    /**
     * 起始的时间戳，基于秒（原版基于毫秒）.
     */
    private final static long START_SECONDS = 1597979276L;

    /**
     * 每一部分占用的位数.
     */
    //序列号占用的位数
    private final static long SEQUENCE_BIT = 16;
    //机器标识占用的位数
    private final static long MACHINE_BIT = 5;

    /**
     * 每一部分的最大值.
     */
    private final static long MAX_MACHINE_NUM = -1L ^ (-1L << MACHINE_BIT);
    private final static long MAX_SEQUENCE = -1L ^ (-1L << SEQUENCE_BIT);

    /**
     * 每一部分向左的位移.
     */
    private final static long MACHINE_LEFT = SEQUENCE_BIT;
    private final static long TIME_LEFT = MACHINE_LEFT + MACHINE_BIT;
    //机器标识
    private long machineId;
    //序列号
    private long sequence;
    //上一次时间戳
    private long lastSeconds = -1L;

    public SnowFlake() {
        this(1);
    }

    public SnowFlake(long machineId) {
        if (machineId > MAX_MACHINE_NUM || machineId < 0) {
            throw new IllegalArgumentException("machineId can't be greater than MAX_MACHINE_NUM or less than 0");
        }
        this.machineId = machineId;
    }

    public static void main(String[] args) throws InterruptedException {
        new SnowFlake().testNewSnowFlake();
    }

    private void testNewSnowFlake() throws InterruptedException {
        AtomicInteger count = new AtomicInteger(0);
        List<Long> totalList = new ArrayList<>(20000);
        Set<Long> totalSet = new HashSet<>(20000);
        SnowFlake snowFlake = this;
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        ExecutorService workProvider = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 1000; i++) {
            workProvider.submit(() -> {
                for (int i1 = 0; i1 < 1000; i1++) {
                    executorService.submit(() -> {
                        System.out.println("--licg---    count.incrementAndGet()  : " + count.incrementAndGet() + "    -----");
                        long id = snowFlake.nextId();
                        synchronized (this){
                            totalList.add(id);
                            totalSet.add(id);
                        }
                        System.out.println("--licg---     snowFlake.nextId() : " + id + "    -----");
                    });
                }
            });
        }
        TimeUnit.SECONDS.sleep(20);
        workProvider.shutdown();
        executorService.shutdown();
        workProvider.awaitTermination(1, TimeUnit.SECONDS);
        executorService.awaitTermination(1, TimeUnit.SECONDS);
        System.out.println("--licg---     totalList.size() : " + totalList.size() + "    -----");
        System.out.println("--licg---     totalSet.size() : " + totalSet.size() + "    -----");
    }

    private static void test1() {
        long ii = 0b1101_0001_111L + 1597979276L;
        System.out.println("--licg---     ii : " + ii + "    -----");
        Instant instant = Instant.ofEpochSecond(ii);
        System.out.println("--licg---  instant    : " + instant + "    -----");

        long currentTimeMillis = System.currentTimeMillis();
        Instant milli = Instant.ofEpochMilli(currentTimeMillis);
        Instant sec = Instant.ofEpochSecond(currentTimeMillis / 1000);
        System.out.println("--licg---     milli : " + milli + "    -----");
        System.out.println("--licg---     sec : " + sec + "    -----");
        System.out.println("--licg---     curSecs : " + currentTimeMillis / 1000 + "    -----");

        SnowFlake snowFlake = new SnowFlake();
        long nextId = snowFlake.nextId();
        String str = Long.toBinaryString(nextId);
        StringBuilder sb = new StringBuilder(str);
        addSeparator(sb);

        System.out.println("--licg---    bin .nextId() : " + sb + "    -----");
        System.out.println("--licg---    oct .nextId() : " + nextId + "    -----");
    }

    // 增加可读性
    private static void addSeparator(StringBuilder sb) {
        for (int i = sb.length(); i > 0; i--) {
            if (i % 4 == 0) {
                sb.insert(sb.length() - i, '_');
            }
        }
    }

    /**
     * 产生下一个ID.
     */
    public synchronized long nextId() {
        long currSeconds = getNewSeconds();
        if (currSeconds < lastSeconds) {
            throw new RuntimeException("Clock moved backwards.  Refusing to generate id");
        }

        if (currSeconds == lastSeconds) {
            //相同秒内，序列号自增
            sequence = (sequence + 1) & MAX_SEQUENCE;
            //同一秒的序列数已经达到最大
            if (sequence == 0L) {
                currSeconds = getNextSeconds();
            }
        } else {
            //不同秒内，序列号置为0
            sequence = 0L;
        }

        lastSeconds = currSeconds;

        return (currSeconds - START_SECONDS) << TIME_LEFT
                | machineId << MACHINE_LEFT
                | sequence;
    }

    private long getNextSeconds() {
        long seconds = getNewSeconds();
        while (seconds <= lastSeconds) {
            seconds = getNewSeconds();
        }
        return seconds;
    }

    private long getNewSeconds() {
        return Instant.now().getEpochSecond();
    }

}

