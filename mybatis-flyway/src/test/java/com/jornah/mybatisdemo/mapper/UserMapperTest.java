package com.jornah.mybatisdemo.mapper;

import com.jornah.mybatisdemo.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
// 指定测试配置文件
// @TestPropertySource({"classpath:common.properties", "classpath:environment.properties"})
class UserMapperTest {
    @Autowired
    UserMapper userMapper;

    @Test
    public void test1(){
        User user=new User();
        user.setFirstName("2131");
        user.setLastName("23234234");
        user.setId(1L);
        User userInfo = userMapper.findUserByCond(user);
        System.out.println("--licg---     userInfo : " + userInfo + "    -----");
    }
}