package com.jornah.interfaces;

public interface Translator {
    public static final String[] I18_JAVA_LANGUAGES = {
            "af", "sq", "ar", "hy", "az", "eu", "be", "bn", "bs", "bg", "ca", "ceb", "ny", "zh-TW", "hr",
            "cs", "da", "nl", "en", "eo", "et", "tl", "fi", "fr", "gl", "ka", "de", "el", "gu", "ht", "ha",
            "iw", "hi", "hmn", "hu", "is", "ig", "id", "ga", "it", "ja", "jw", "kn", "kk", "km", "ko", "lo",
            "la", "lv", "lt", "mk", "mg", "ms", "ml", "mt", "mi", "mr", "mn", "my", "ne", "no", "fa", "pl",
            "pt", "ro", "ru", "sr", "st", "si", "sk", "sl", "so", "es", "su", "sw", "sv", "tg", "ta", "te",
            "th", "tr", "uk", "ur", "uz", "vi", "cy", "yi", "yo", "zu"
    };

    String translate(String word,String srcLan,String tarLan);

    /**
     * 自动检测翻译原文本语种
     *
     * @param word 要翻译的文本
     * @param tarLan 目标语言
     */
    String translate(String word,String tarLan);
}
