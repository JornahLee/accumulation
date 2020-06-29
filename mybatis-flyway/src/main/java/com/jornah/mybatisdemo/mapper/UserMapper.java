package com.jornah.mybatisdemo.mapper;

import com.jornah.mybatisdemo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    User findUserInfo(Long id);
    User findUserByCond(User user);
}
