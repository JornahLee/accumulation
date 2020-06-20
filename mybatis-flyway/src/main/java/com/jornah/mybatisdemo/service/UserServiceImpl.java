package com.jornah.mybatisdemo.service;

import com.jornah.mybatisdemo.entity.User;
import com.jornah.mybatisdemo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {
    // @Autowired
    // UserMapper userMapper;
    public User findUserById(Long id){
        // User userInfo = userMapper.findUserInfo(id);
        return new User();
    }

}
