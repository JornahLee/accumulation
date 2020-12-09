package com.jornah.mybatisdemo.springcache;


import com.jornah.mybatisdemo.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestCacheable {

    @Autowired
    UserServiceImpl userService;

    @Test
    public void testCache(){
        System.out.println(userService.getInfo());

    }

}
