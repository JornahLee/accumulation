package com.jornah.javabase;

import java.time.Instant;

import static java.time.Instant.now;

public class InstantDemo {
    public static void main(String[] args) {
        // <

        // a =1 , b=2
        // a < b  , t
        // b < a  , f
        Instant tokenCreateAt=now();
        Instant jwtCreateAt=now().minusSeconds(1);
        // 没过期
        boolean isBefore = jwtCreateAt.isBefore(tokenCreateAt);
        if(isBefore){
            System.out.println("--licg---     guoqi  -----");
        }

        System.out.println("--licg---     isBefore : " + isBefore + "    -----");
    }
}
