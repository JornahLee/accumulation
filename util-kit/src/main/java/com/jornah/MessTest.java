package com.jornah;

import javafx.scene.input.TouchEvent;
import org.apache.commons.lang3.RandomUtils;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class MessTest {
    public static void main(String[] args) {
        Instant value= Instant.now();
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .appendInstant(0)
                .toFormatter();

        // formatter.withZone(ZoneId.of())
        String format = formatter.format(value);
        System.out.println("--licg---     value  : " + value + "    -----");
        System.out.println("--licg---     format : " + format + "    -----");


    }

    private static void calcLogic() {
        Object o = null;
        // 备忘一下，A||B ，A为真就不会继续判断B了，  A && B，A为假也不会继续判断了
        if (o == null || o.hashCode() < 0) {
            System.out.println("--licg---     false : " + false + "    -----");
        }
    }

    private static void testLongHashLower() {
        Set<Long> set = new HashSet<>(100_0000);
        Set<Long> randomSet = new HashSet<>(100_0000);
        for (int i = 0; i < 6_0000; i++) {
            long randomValue = RandomUtils.nextLong();
            Long aLong = myHashCode(randomValue);
            randomSet.add(randomValue);
            set.add(aLong);
        }
        System.out.println("--licg---     set.size() : " + set.size() + "    -----");
        System.out.println("--licg---     randomSet.size() : " + randomSet.size() + "    -----");
    }

    private static long myHashCode(Long value) {
        // return value.hashCode() & 0x00000000ff_ff_ff_ff;
        return value % 0x000000000f_ff_ff_ff;
    }

    private static void testFiter() {
        List<User> list = new ArrayList<>();
        list.add(new User(1L, "12", "111"));
        list.add(new User(1L, "12", "111"));
        list.add(new User(1L, "12", "111"));
        list.sort(Comparator.comparing(User::getId));
        List<User> users = Optional.ofNullable(list).filter(List::isEmpty).orElseGet(ArrayList::new);
        users.forEach(System.out::println);
    }

    private static void test1() {
        for (int i = 0; i < 10; i++) {
            System.out.println("--licg---     i : " + i + "    -----");
            if (i == 4) {
                if (i == 4) {
                    break;
                }
            }
        }
    }

    private static class User {
        private Long id;
        private String name;
        private String pwd;

        public User(Long id, String name, String pwd) {
            this.id = id;
            this.name = name;
            this.pwd = pwd;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }
    }
}
