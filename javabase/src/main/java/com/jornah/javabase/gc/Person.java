package com.jornah.javabase.gc;

public class Person {
    private final Person father;
    public Person(Person father) {
        this.father=father;
    }
    public Person() {
        father=null;
    }

}
