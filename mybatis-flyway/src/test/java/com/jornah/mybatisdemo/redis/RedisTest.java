package com.jornah.mybatisdemo.redis;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class RedisTest {

    // StringRedisTemplate 与RedisTemplate的区别就是，前者指定了序列化器
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    public void testPipeLine() {
        List<String> list = Arrays.asList("k1", "k2", "k3", "k4");
        List list1 = redisTemplate.executePipelined(
                (RedisCallback<String>) connection -> {
                    for (String key : list) {
                        connection.get(key.getBytes());
                    }
                    return null;
                }
        );
        list1.stream().map(String::valueOf).forEach(System.out::println);

    }

    @Test
    public void testPipeLineZscore() {
        String key = "k";
        List<String> members = Arrays.asList("k1", "k2", "k3", "k4", "k5", "k7");
        List<Object> list1 = redisTemplate.executePipelined(
                (RedisCallback<Long>) connection -> {
                    for (String member : members) {
                        connection.zScore(key.getBytes(), member.getBytes());
                    }
                    // 只能返回null
                    return null;
                }
                // 不指定序列化器就会报序列化异常。。。
                // , new StringRedisSerializer()
        );
        System.out.println("size:"+list1.size());

        list1.stream().forEach(System.out::println);

    }

}
