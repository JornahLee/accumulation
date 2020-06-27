package com.jornah.mybatisdemo.service;

import com.jornah.mybatisdemo.entity.User;
import com.jornah.mybatisdemo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl {
    @Autowired
    UserMapper userMapper;

    @Transactional
    public void trans() {
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
