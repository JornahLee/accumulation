package com.jornah.mybatisdemo.mapper.interceptor;


import org.apache.commons.lang3.RandomUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

import java.lang.reflect.Field;
import java.util.Properties;

@Intercepts({
        @Signature(
                type = Executor.class,
                method = "update",
                args = {MappedStatement.class, Object.class}
        )
})
public class MybatisInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement)invocation.getArgs()[0];

        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
        Object parameter = invocation.getArgs()[1];
        String sql = mappedStatement.getBoundSql(parameter).getSql();
        // 这里最好用注解来实现好一些
        if(sql.contains("users")||sql.contains("user_devices")||sql.contains("time_change_audits")){
            Class<?> clazz = parameter.getClass().getSuperclass().getSuperclass();
            updateField(clazz.getDeclaredFields(),parameter,sqlCommandType);
        }

        return invocation.proceed();
    }
    private void updateField(Field[] declaredFields, Object parameter, SqlCommandType sqlCommandType) throws IllegalAccessException {
        for (Field field: declaredFields){
            if (SqlCommandType.INSERT.equals(sqlCommandType)){
                if (field.getName().equals("id")){
                    field.setAccessible(true);
                    field.set(parameter, RandomUtils.nextInt());
                }
            }
        }
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}


