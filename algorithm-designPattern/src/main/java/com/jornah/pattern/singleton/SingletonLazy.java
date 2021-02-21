package com.jornah.pattern.singleton;

import java.util.Objects;

// 懒汉式
public class SingletonLazy {
    private static SingletonLazy obj;

    private SingletonLazy() {
    }

    public SingletonLazy getInstance() {
        return get2();
    }

    // 并发时会出现多例的情况， 如： 线程A 执行isNull时失去CPU时间片，其他线程去new对象，此时线程A恢复时候会再new一个
    // 导致出现多例
    public static SingletonLazy get1() {
        if (Objects.isNull(obj)) {
            obj = new SingletonLazy();
        }
        return obj;
    }

    // 虽然解决了线程安全问题，是不是存在性能问题呢
    // 每次都判断锁很消耗性能，但单例创建对象的情况只有一次，所以增加一次空检查，在大多数情况下可以避免锁判断
    public static SingletonLazy get2() {
        if (Objects.isNull(obj)) {  // 在Line 1
            synchronized (SingletonLazy.class) {// 在Line 2
                if (Objects.isNull(obj)) {// 在Line 3
                    obj = new SingletonLazy();// 在Line 4
                }
            }
        }
        return obj; // 并发读写会存在线程安全问题，但是并发读不会，所以可以把读从同步代码块提出来
    }

}
