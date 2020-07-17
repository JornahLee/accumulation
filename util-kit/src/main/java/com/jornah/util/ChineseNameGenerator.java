package com.jornah.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ChineseNameGenerator {
    private List<String> surName ;
    private List<String> doubleSurName ;
    private List<String> chineseWord ;

    public static void main(String[] args) throws FileNotFoundException {
        ChineseNameGenerator chineseNameGenerator = new ChineseNameGenerator();
        String s = chineseNameGenerator.randomName(false, 3);
        System.out.println("--licg---     s : " + s + "    -----");
    }
    // @Test
    // public void contextLoads() {
    //     System.out.println(randomName(true, 3));//三位数
    //     System.out.println(getRandomJianHan(3));
    // }

    public ChineseNameGenerator() throws FileNotFoundException {
        InputStream firstNameInput = this.getClass().getClassLoader().getResourceAsStream("first-name.json");
        InputStream chineseWordInput = this.getClass().getClassLoader().getResourceAsStream("chinese-word.json");

        Gson gson = new GsonBuilder().create();
        Map<String, List<String>> firstNames = gson.fromJson(new InputStreamReader(firstNameInput),
                new TypeToken<Map<String, List<String>>>() {
                }.getType());
        this.surName = firstNames.get("surName");
        this.doubleSurName = firstNames.get("doubleName");

        Map<String, List<String>> chineseWord = gson.fromJson(new InputStreamReader(chineseWordInput),
                new TypeToken<Map<String, List<String>>>() {
                }.getType());
        this.chineseWord = chineseWord.get("word");
    }

    /**
     * 方法1
     */
    public static String getRandomJianHan(int len) {
        String randomName = "";
        for (int i = 0; i < len; i++) {
            String str = null;
            int highPos, lowPos; // 定义高低位
            Random random = new Random();
            highPos = (176 + Math.abs(random.nextInt(39))); // 获取高位值
            lowPos = (161 + Math.abs(random.nextInt(93))); // 获取低位值
            byte[] b = new byte[2];
            b[0] = (new Integer(highPos).byteValue());
            b[1] = (new Integer(lowPos).byteValue());
            try {
                str = new String(b, "GBK"); // 转成中文
            } catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
            }
            randomName += str;
        }
        return randomName;
    }

    public String randomName(boolean simple, int len) {

        int surNameLen = surName.size();
        int doubleSurNameLen = doubleSurName.size();
        int chineseWordLen = chineseWord.size();

        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        if (simple) {
            sb.append(surName.get(random.nextInt(surNameLen)));
            int surLen = sb.toString().length();
            for (int i = 0; i < len - surLen; i++) {
                if (sb.toString().length() <= len) {
                    sb.append(chineseWord.get(random.nextInt(chineseWordLen)));
                }
            }
        } else {
            sb.append(doubleSurName.get(random.nextInt(doubleSurNameLen)));
            int doubleSurLen = sb.toString().length();
            for (int i = 0; i < len - doubleSurLen; i++) {
                if (sb.toString().length() <= len) {
                    sb.append(chineseWord.get(random.nextInt(chineseWordLen)));
                }
            }
        }
        return sb.toString();
    }
}