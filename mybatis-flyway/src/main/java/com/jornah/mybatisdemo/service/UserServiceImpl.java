package com.jornah.mybatisdemo.service;

import com.jornah.mybatisdemo.entity.User;
import com.jornah.mybatisdemo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl {

    @Autowired
    private UserMapper userMapper;

    public User findUserById(Long id) {
        User userInfo = userMapper.findUserInfo(id);
        return new User();
    }

    @Transactional
    public void trans() {
        User userInfo = userMapper.findUserInfo(1L);
        System.out.println("--first time---     userInfo : " + userInfo + "    -----\n");

        userInfo = userMapper.findUserInfo(1L);
        System.out.println("--again---     userInfo : " + userInfo + "    -----\n");

        userInfo.setFirstName("updated value");
        userMapper.updateUser(userInfo);

        userInfo = userMapper.findUserInfo(1L);
        System.out.println("--get after update---     userInfo : " + userInfo + "    -----\n");
        noTrans();

    }

    public void noTrans() {
        User userInfo = userMapper.findUserInfo(1L);
        System.out.println("--noTrans first time---     userInfo : " + userInfo + "    -----\n");

        userInfo = userMapper.findUserInfo(1L);
        System.out.println("--noTrans again---     userInfo : " + userInfo + "    -----\n");


    }

}
