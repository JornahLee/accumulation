package com.jornah.javabase.aboutextend;

public class Cat extends Animal{
    public static int count=2;
    public int age=2;
    public Cat() {
       
        this.getAge();
        System.out.println("super getAge:"+super.getAge());
        
    }
    public void run() {
        System.out.println("Cat run");
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public static void main(String args[]) {
        new Cat().run();
    }
}
