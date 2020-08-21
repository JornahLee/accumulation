//////////////////////////////////////////////////////////////////////////
//
// Copyright (c) 2018, Qisike Limited. All rights reserved.
//
// This is unpublished proprietary source code of Qisike Limited.
// The copyright notice above does not evident any actual or intended
// publication of such source code.
//
//////////////////////////////////////////////////////////////////////////

package com.jornah.mybatisdemo.config;


import com.jornah.mybatisdemo.mapper.interceptor.AddConditionInterceptor;
import com.jornah.mybatisdemo.mapper.interceptor.MybatisInterceptor;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class MybatisConfiguration {

    @Autowired
    private AddConditionInterceptor addConditionInterceptor;

    @Bean
    public ConfigurationCustomizer mybatisConfigurationCustomizer() {
        return configuration -> {
            // 可以调用properties.setProperty方法来给拦截器设置一些自定义参数

            // 如果拦截器交给spring管理，会自动注册生效，此处再次配置会导致重复注册
            // configuration.addInterceptor(addConditionInterceptor);
            // configuration.addInterceptor(new MybatisInterceptor());

        };
    }


}
