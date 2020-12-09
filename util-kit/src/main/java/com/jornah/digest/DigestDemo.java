package com.jornah.digest;


import io.jsonwebtoken.impl.TextCodec;
import org.apache.commons.codec.digest.DigestUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

// 摘要算法工具类使用 Digest（摘要）
public class DigestDemo {
    private static void sha1Demo() {
        // 使用摘要算法进行签名/加密时：通常需要使用加盐()到末尾，再进行hash，以增加彩虹表破解难度
        String salt = "xxxxx";
        double rewardAmount=1.000000;
        long sessionId=123129L;
        long userId=123441;
        // sha1算法的结果是byte[]类型，直接转化为字符串不可读，所以使用hex或者base64进行可读性转码，DigestUtils只提供了hex转码
        String res= DigestUtils.sha1Hex(String.format("%f%d%d%s", rewardAmount, sessionId, userId, salt));
        System.out.println("--licg---     res : " + res + "    -----");
    }

    /**
     *  Hash-based Message Authentication Code = Hmac （消息验证码、消息签名）
     */
    public static void testHmacSHA256() throws NoSuchAlgorithmException, InvalidKeyException {
        String text="eyJhbG";
        String secret="sdf#22#2234";
        Mac hmacSha256 = Mac.getInstance("HmacSHA256");
        hmacSha256.init(new SecretKeySpec(TextCodec.BASE64.decode(secret),  "HmacSHA256"));
        byte[] hmacSha256Bytes = hmacSha256.doFinal(text.getBytes());
        System.out.println("\n------");
        System.out.println("TextCodec.BASE64URL.encode("+TextCodec.BASE64URL.encode(hmacSha256Bytes));

    }
}
