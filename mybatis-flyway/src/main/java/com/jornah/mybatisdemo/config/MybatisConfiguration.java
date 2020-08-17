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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class MybatisConfiguration {

    @Bean
    public ConfigurationCustomizer mybatisConfigurationCustomizer() {
        AddConditionInterceptor interceptor = new AddConditionInterceptor();
        Properties properties = new Properties();
        // 可以调用properties.setProperty方法来给拦截器设置一些自定义参数
        interceptor.setProperties(properties);
        return configuration -> {
            configuration.addInterceptor(new AddConditionInterceptor());
            configuration.addInterceptor(new MybatisInterceptor());

        };
    }


}
