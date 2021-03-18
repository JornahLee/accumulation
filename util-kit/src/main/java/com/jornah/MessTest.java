package com.jornah;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;
import org.springframework.util.Base64Utils;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

public class MessTest {
    public static void main(String[] args) throws IOException, InterruptedException {

        boolean matches = "192.151.123-1".matches(".*[-/].*");
        System.out.println(matches);
        String[] split = "123".split(",");
        System.out.println(split.length);
        for (String s : split) {
            System.out.println(s);
        }
        System.out.println(split);


        Process process = Runtime.getRuntime().exec("ls");
        ByteOutputStream bout=new ByteOutputStream();
        org.springframework.util.StreamUtils.copy(process.getInputStream(),bout);
        System.out.println(bout.toString());

        long i = 14716594356225L;
        System.out.println(i % 8);

        String[] strings = new String[]{"123", "123", "123", "123"};
        Object[] objects = new Object[]{"123", "123", "123", "123"};
        setObjs((Object[]) strings);

        List<Integer> li = new ArrayList<>();
        li.add(10);
        li.add(2);
        li.add(3);
        li.add(1);
        // 默认升序排列
        li.sort(Comparator.comparingInt(val -> (int) val).reversed());
        li.forEach(System.out::println);


        // ArrayList<String> list = new ArrayList<>();
        // list.add("123");
        // list.add("123");
        // list.add("123");
        // list.add("123");
        // String[] strings = list.toArray(new String[0]);
        // for (String string : strings) {
        //     System.out.println(string);
        // }


        String s = Base64Utils.encodeToString("elastic:mimaelk614".getBytes());
        System.out.println(s);



    }

    public static void setObjs(Object... objs) {
        Stream.of(objs).forEach(System.out::println);

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

    @Test
    public  void test(){
        for (int i = 0; i < 10; i++) {
            List<String> ips=new ArrayList();
            ips.add("123");
            ips.add("...");

            if (ips.size()<=1) {
                return;
            }
            int randomInt = RandomUtils.nextInt(0, ips.size());
            String randomEle = ips.get(randomInt);
            String first = ips.get(0);
            ips.set(randomInt,first);
            ips.set(0,randomEle);

            ips.forEach(System.out::println);
            System.out.println("--licg---     i : " + i + "    -----");
        }
    }
    @Test
    public void time(){
        System.out.println(Instant.ofEpochMilli(1606447255144L));
        System.out.println(Instant.ofEpochMilli(1609975944397L));
        System.out.println("--------");
        System.out.println(Instant.ofEpochMilli(1608638807346L));
        System.out.println(Instant.ofEpochMilli(1608639956816L));
        // Queue<xx> que= new LinkedList();
        // Object poll = que.poll();

    }
}
