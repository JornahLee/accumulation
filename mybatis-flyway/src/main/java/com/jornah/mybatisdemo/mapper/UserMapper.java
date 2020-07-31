package com.jornah.mybatisdemo.mapper;

import com.jornah.mybatisdemo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    User findUserInfo(Long id);
    Long addUser(User user);
    Long insertUser(User user);
    Long replaceUser(User user);
    void updateUser(User user);
    User findUserByCond(User user);
    void truncateTable();
    List<User> findBy(@Param("id") Long id, @Param("fetchSize") int fetchSize);
}
