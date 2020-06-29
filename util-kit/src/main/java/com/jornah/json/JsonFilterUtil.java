package com.jornah.json;



import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.google.common.collect.Sets;

/**
 * @author  licong
 * @date  2020/6/29 下午5:50
 * 不解析指定json字段（还可以通过pojo上加注解的方式实现全局范围的过滤）
 *
 */
public final class JsonFilterUtil {

    public static void addFilterForMapper(ObjectMapper mapper, Class targetBean, String[] excludeFields) {
        SimpleBeanPropertyFilter fieldFilter = SimpleBeanPropertyFilter.serializeAllExcept(
                Sets.newHashSet(excludeFields));
        SimpleFilterProvider filterProvider = new SimpleFilterProvider().addFilter("fieldFilter", fieldFilter);
        mapper.setFilterProvider(filterProvider).addMixIn(targetBean, FieldFilterMixIn.class);
    }

    public static void addFilterForMapper(ObjectMapper mapper, Class targetBean, String excludeField) {
        addFilterForMapper(mapper, targetBean, new String[]{excludeField});
    }

    @JsonFilter("fieldFilter")
    interface FieldFilterMixIn {
    }
}
