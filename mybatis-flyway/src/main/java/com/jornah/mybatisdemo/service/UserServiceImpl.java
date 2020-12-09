package com.jornah.mybatisdemo.service;

import com.jornah.mybatisdemo.entity.User;
import com.jornah.mybatisdemo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl {
    public static final String k1 = "k1";

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

    @Cacheable(cacheNames = "test-caches", key = "#root.target.k1")
    public String getInfo() {
        return "woshishei";
    }
    @CacheEvict(cacheNames = "test-caches", key = "#root.target.k1")
    public void update() {

    }

}
