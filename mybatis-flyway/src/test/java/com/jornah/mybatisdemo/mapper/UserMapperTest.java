package com.jornah.mybatisdemo.mapper;

import com.jornah.mybatisdemo.entity.User;
import com.jornah.mybatisdemo.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
// 指定测试配置文件
// @TestPropertySource({"classpath:common.properties", "classpath:environment.properties"})
class UserMapperTest {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    UserMapper userMapper;


    @Test
    public void test1() {
        //同一个类中，transactional方法A调用 私有非transactional方法调用方法B， A和B的SQL会同出一个事务中
        // 也就是说 内部调用，被调用的方法上注解会失效。 从上层分别调用A，B方
        userService.trans();

        //  update insert 更新操作会自动刷新二级缓存，  也是 mapper文件中select标签中的  flushcache默认是 true的，  二级缓存的作用域是 mapper文件的namespace
        // 看似，这个是不会出现缓存不一致的情况
        // 但是如果 一旦是集群部署的情况，   虽然二级缓存作用域是 mapper的namespace ， 不同机器是无法刷新 其他机器的namespace的
        // 所以 集群部署下，  需要将 flushcache 置为true ， 也就是刷新的本机缓存。
        // 刷新本机缓存的话，没用， 所以我觉得需要使用redis来实现二级缓存
        // 那项目里 显式 的 flushcache的作用是什么？ 无意义的写法？  （我草，我发现项目里都没有开启二级缓存？？？  这个写的真 tm好像是没有意义的，  到时候再测试一下
        // 是否开启了二级缓存）
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
        userMapper.findBy(1L,1);
        userMapper.findBy(1L,-1);
        userMapper.findBy(1L,0);

    }

}