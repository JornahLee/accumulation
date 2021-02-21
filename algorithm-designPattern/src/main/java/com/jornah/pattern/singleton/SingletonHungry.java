package com.jornah.pattern.singleton;

// 饿汉式
public class SingletonHungry {
    private static SingletonHungry obj = new SingletonHungry();

    private SingletonHungry() {
    }

    public static SingletonHungry get() {
        return obj;
    }

}
