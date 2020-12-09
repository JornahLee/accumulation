package com.jornah.javabase;

import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

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

    /**
     * Instant时间戳，精确秒后 x 位
     */
    @Test
    public void instantAccurate(){
        Instant value= Instant.now();
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .appendInstant(0)
                .toFormatter();

        String format = formatter.format(value);
        System.out.println("--licg---     value  : " + value + "    -----");
        System.out.println("--licg---     format : " + format + "    -----");
    }

    public void dateFormat(){
        Instant now = now();
        String format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.systemDefault())
                .format(now);
        System.out.println(format);
    }
}
