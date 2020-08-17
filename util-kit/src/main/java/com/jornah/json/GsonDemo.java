package com.jornah.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;
import com.jornah.pojo.TestGson;
import com.jornah.util.GsonExclusionStrategy;

import java.lang.reflect.Type;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class GsonDemo {
    public static void main(String[] args) {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Instant.class, (JsonSerializer) (src, typeOfSrc, context) -> {
            if (typeOfSrc.equals(Instant.class)) {
                System.out.println("--licg---     typeOfSrc : " + typeOfSrc + "    -----");
                Instant instant = (Instant) src;
                String format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.systemDefault()).format(instant);
                return new JsonPrimitive(format);
            }
            return new JsonPrimitive(src.toString());
        });
        Gson gson = builder.create();
        String s = gson.toJson(Instant.now());
        System.out.println("--licg---     s : " + s + "    -----");
    }

    private static void fun1() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("硕大的1");
        strings.add("硕大的2");
        strings.add("硕大的3");
        strings.add("硕大的4");
        strings.add("硕大的5");
        strings.add("硕大的6");
        Gson gson = new GsonBuilder().create();
        String s = gson.toJson(strings);
        System.out.println("s = " + s);
    }

    public static void deSerialize() {
        Gson gson = new GsonBuilder().create();
        String json = "[{\"id\":16,\"title\":\"title  这是 US 的 通用端的 英语\"},{\"id\":19,\"title\":\"title  这是 US 安卓端的 的英语\"}]";
        // Object o = gson.fromJson(json, new TypeToken<ArrayList<Item>>() {}.getType());
        List<Item> usersList = gson.fromJson(json,
                new TypeToken<List<Item>>() {
                }.getType());
        // Object fromJson = gson.fromJson(json, typeOfT);
        usersList.forEach(System.out::println);

    }
    public static class ForTest{
        String user_id;

        public ForTest(String user_id) {
            this.user_id = user_id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }
    }

    public class Item {
        String id;
        String title;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "id='" + id + '\'' +
                    ", title='" + title + '\'' +
                    '}';
        }
    }

    public static void excludeFieldByAnnotation() {
        GsonBuilder builder = new GsonBuilder();
        builder.setExclusionStrategies(new GsonExclusionStrategy(Arrays.asList("age")));
        Gson gson = builder.create();
        TestGson lic = new TestGson(1L, "lic", 11);
        String json = gson.toJson(lic);
        System.out.println("--licg---     json : " + json + "    -----");

    }
}
