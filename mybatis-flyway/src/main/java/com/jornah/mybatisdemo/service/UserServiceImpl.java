package com.jornah.mybatisdemo.service;

import com.jornah.mybatisdemo.entity.User;
import com.jornah.mybatisdemo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl {

    @Value("${com.mysql.pwd}")
    String value;

    @Autowired
    private UserMapper userMapper;

    public User findUserById(Long id) {
        User userInfo = userMapper.findUserInfo(id);
        return new User();
    }

    @Transactional
    public void trans() {
        System.out.println("--licg---     value : " + value + "    -----");
        System.out.println();
        System.out.println();
        System.out.println();
        User userInfo = userMapper.findUserInfo(1L);
        System.out.println("--first time---     userInfo : " + userInfo + "    -----\n");

        userInfo = userMapper.findUserInfo(1L);
        System.out.println("--again---     userInfo : " + userInfo + "    -----\n");

        userInfo.setFirstName("updated value");
        userMapper.updateUser(userInfo);
        // System.out.println("--update---     userInfo : " + userInfo + "    -----\n");

        userInfo = userMapper.findUserInfo(1L);
        System.out.println("--get after update---     userInfo : " + userInfo + "    -----\n");

        int i = 10 / 0;
    }

}
