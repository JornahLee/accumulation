package com.jornah.javabase;

import java.time.Instant;

import static java.time.Instant.now;

public class Java8TimeDemo {
    public static void main(String[] args) {
        // <

        // a =1 , b=2
        // a < b  , t
        // b < a  , f
        Instant dbTokenCreateAt=now().minusSeconds(1);
        Instant jwtCreateAt=now();
        // 没过期
        boolean isExpire = jwtCreateAt.isBefore(dbTokenCreateAt);
        if(isExpire){
            System.out.println("--licg---     guoqi  -----");
        }

        System.out.println("--licg---     isExpire : " + isExpire + "    -----");
    }
}
