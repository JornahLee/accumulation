package com.jornah.javabase;

public class EnumDemo {
    public static void main(String[] args) {
        Name name= Name.ZHANG_SAN;
        System.out.println("--licg---     name : " + name + "    -----");
    }

    public enum Name {
        ZHANG_SAN,LI_SI
    }
}
