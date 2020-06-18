package com.jornah.util;

import java.util.Map;

public class MapUtil {
    public static boolean containsIgnoreCase(Map<String,String> map, String key){
        if(map.containsKey(key)){
            return true;
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if(entry.getKey().equalsIgnoreCase(key)){
                return true;
            }
        }
        return false;
    }
}
