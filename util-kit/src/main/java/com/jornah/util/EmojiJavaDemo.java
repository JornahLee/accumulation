package com.jornah.util;

import com.vdurmont.emoji.EmojiParser;

public class EmojiJavaDemo {
    public static void main(String[] args) {

        String str="我是算法上单发😈";
        String res = EmojiParser.parseToAliases(str);//直接转换
        System.out.println("--licg---     res : " + res + "    -----");
        String decodeEmoji = EmojiParser.parseToUnicode(res);
        System.out.println("--licg---     decodeEmoji : " + decodeEmoji + "    -----");
        String decodeEmojiWithEMoji = EmojiParser.parseToUnicode(str);
        System.out.println("--licg---     decodeEmoji : " + decodeEmojiWithEMoji + "    -----");
    }


}
