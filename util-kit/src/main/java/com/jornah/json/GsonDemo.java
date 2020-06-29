package com.jornah.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class GsonDemo {
    public static void main(String[] args) {
        deSerialize();
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
                new TypeToken<List<Item>>(){}.getType());
        // Object fromJson = gson.fromJson(json, typeOfT);
        usersList.forEach(System.out::println);

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
}
