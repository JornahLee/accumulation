package com.jornah.util;

import com.jornah.pojo.UserDO;
import com.jornah.pojo.UserDTO;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;

/**
 * @author LiCong
 */
public class SpringBeanUtilDemo {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        int res="2".compareTo("1");
        System.out.println("--licg---  res : " + res + "-----");
        UserDO userDO=new UserDO();
        userDO.setUsername("licong");
        userDO.setNickname("jornah");
        userDO.setPassword("mypwd");
        userDO.setEmail("12313@qq.com");
        UserDTO userDTO=new UserDTO();
        BeanUtils.copyProperties(userDO,userDTO);
        System.out.println("--licg---  userDO : " + userDO + "-----");
        System.out.println("--licg---  userDTO : " + userDTO + "-----");

    }
}
