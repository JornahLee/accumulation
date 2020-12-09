package com.jornah.mybatisdemo.mapper.interceptor;


import com.jornah.mybatisdemo.entity.BaseEntity;
import org.apache.commons.lang3.RandomUtils;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

@Intercepts({
        @Signature(
                type = Executor.class,
                method = "update",
                args = {MappedStatement.class, Object.class}
        )
})
// @Component
public class MybatisInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
        Object parameter = invocation.getArgs()[1];
        String sql = mappedStatement.getBoundSql(parameter).getSql();
        // 这里最好用注解来实现好一些
        if (sql.contains("user") || sql.contains("user_devices") || sql.contains("time_change_audits")) {
            // Class<?> clazz = parameter.getClass().getSuperclass().getSuperclass();
            updateField(null, parameter, sqlCommandType);
        }

        return invocation.proceed();
    }

    private void updateField(Field[] declaredFields, Object parameter, SqlCommandType sqlCommandType) throws IllegalAccessException {
        if (parameter instanceof BaseEntity) {
            BaseEntity baseEntity = (BaseEntity) parameter;
            baseEntity.setId(RandomUtils.nextLong());
        } else if (parameter instanceof MapperMethod.ParamMap) {
            // mapper方法参数为集合时，获取的方法的参数parameter，是封装在一个map里的
            // 这样设计 目的应该是为了方便动态sql foreach使用
            MapperMethod.ParamMap<?> map = (MapperMethod.ParamMap<?>) parameter;
            List<?> param = (List<?>)map.get("collection");
            param.stream()
                    .filter(obj -> Objects.nonNull(obj) && (obj instanceof BaseEntity))
                    .map(obj -> (BaseEntity) obj)
                    .forEach(obj -> obj.setId(RandomUtils.nextLong()));
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


