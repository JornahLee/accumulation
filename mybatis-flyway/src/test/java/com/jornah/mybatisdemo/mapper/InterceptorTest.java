package com.jornah.mybatisdemo.mapper;

import com.jornah.mybatisdemo.entity.User;
import com.jornah.mybatisdemo.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class InterceptorTest {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    UserMapper userMapper;


    @Test
    public void test1() {
        userService.trans();
    }

    @Test
    public void test2() {
        userMapper.truncateTable();
        User user = new User();
        user.setId(1);
        user.setFirstName("fn 1");
        user.setLastName("ln 1");
        userMapper.insertUser(user);
        userMapper.insertUser(user);
    }
    @Test
    public void testReplaceGrammar() {
        userMapper.truncateTable();
        User user = new User();
        user.setId(1);
        user.setFirstName("fn 1");
        user.setLastName("ln 1");
        userMapper.replaceUser(user);
        user.setFirstName("fn 2");
        user.setLastName("ln 2");
        userMapper.replaceUser(user);
    }

    @Test
    public void testIfTag(){
        List<User> by = userMapper.findBy(1L, 1);
        for (User user : by) {
            System.out.println("--licg---     user : " + user + "    -----");
        }

    }

}