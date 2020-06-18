package com.jornah.util;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author LiCong
 */
public class MyStringUtil {
    public static void main(String[] args) {
        String line="LiC-ong";
        ArrayList<String> strings = containsString(line, "\b" + "LiC" + "\b");
        for (String string : strings) {
            System.out.println("--licg---  string : " + string + "-----");
        }
    }
    public static ArrayList<String> containsString(String sourceStr, String regex){
        if(sourceStr==null || "".equals(sourceStr.trim())){
            return null;
        }
        if(regex==null || "".equals(regex.trim())){
            return null;
        }
        ArrayList<String> resultList=new ArrayList<>();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(sourceStr);
        while(matcher.find()){
            resultList.add(matcher.group());
        }
        return resultList;
    }
}
