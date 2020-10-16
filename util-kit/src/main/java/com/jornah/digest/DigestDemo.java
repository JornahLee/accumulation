package com.jornah.digest;


import org.apache.commons.codec.digest.DigestUtils;

// 摘要算法工具类使用 Digest（摘要）
public class DigestDemo {
    public static void main(String[] args) {
        // 使用摘要算法进行签名/加密时：通常需要使用加盐()到末尾，再进行hash，以增加彩虹表破解难度
        String salt = "xxxxx";
        double rewardAmount=1.000000;
        long sessionId=123129L;
        long userId=123441;
        // sha1算法的结果是byte[]类型，直接转化为字符串不可读，所以使用hex或者base64进行可读性转码，DigestUtils只提供了hex转码
        String res= DigestUtils.sha1Hex(String.format("%f%d%d%s", rewardAmount, sessionId, userId, salt));
        System.out.println("--licg---     res : " + res + "    -----");
    }
}
