package com.jornah.util.json;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.GsonBuilder;
import com.jornah.pojo.Item;
import com.jornah.pojo.UserDO;

import java.io.IOException;
import java.util.List;

public class JacksonDemo {
    public static void main(String[] args) throws IOException {
        deSerialize();
    }

    private static void fun1() throws JsonProcessingException {
        UserDO userDO = new UserDO();
        ObjectMapper objectMapper=new ObjectMapper();
        String jackson = objectMapper.writeValueAsString(userDO);
        String s = new GsonBuilder().create().toJson(userDO);
        // System.out.println("s = " + s);
        System.out.println("jackson = " + jackson);
    }

    public static void deSerialize()
            throws JsonParseException, JsonMappingException, IOException {
        String jsonString="[{\"id\":16,\"title\":\"title  这是 US 的 通用端的 英语\"},{\"id\":19,\"title\":\"title  这是 US 安卓端的 的英语\"}]";
        ObjectMapper mapper = new ObjectMapper();
        JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, Item.class);
        List<Item> lst = mapper.readValue(jsonString, javaType);

        for (Item item : lst) {
            System.out.println("--licg---     item : " + item + "    -----");
        }
    }

}
