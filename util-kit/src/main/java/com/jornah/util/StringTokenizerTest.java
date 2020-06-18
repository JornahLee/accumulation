package com.jornah.util;

import java.util.StringTokenizer;

public class StringTokenizerTest {
    public static void main(String[] args) {
        String str="";
        StringTokenizer sToken=new StringTokenizer(str);
        while(sToken.hasMoreTokens()) {
            String string = sToken.nextToken();
            System.out.println(string+":length:"+string.length());
        }
    }
    public void test2() {
        String str="OPOCPR101D OPOCFC101D OPOCPR102D OPOCDO101D OPODCA101D OPOBND200R";
    }
}
