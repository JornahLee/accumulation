package com.jornah.encrpty;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import static java.nio.charset.StandardCharsets.UTF_8;
import static javax.crypto.Cipher.DECRYPT_MODE;
import static javax.crypto.Cipher.ENCRYPT_MODE;

public class AesDemo {
    private static final String AES = "AES";

    private Cipher cipher;
    private SecretKeySpec secretKeySpec;
    private Base64 base64;

    // private String secretKey = "panda&local#1234";
    private String secretKey = "panda&beta#12345";

    public static void main(String[] args) throws Exception {
        AesDemo aesDemo = new AesDemo();
        aesDemo.init();
        // String encode = aesDemo.encode("1212");
        // String encode1 = aesDemo.encode("1212");
        // System.out.println(encode);
        // System.out.println(encode1);
        // System.out.println(aesDemo.decode(encode));
        // System.out.println(aesDemo.decode(encode1));
        // {"ipv6":false,"ovpn":{"CN":"https://pkg.pandacabin.pw/open-vpn/NMJYd9Opk5POdqQV.ovpn","OTHERS":"https://pkg.pandacabin.pw/open-vpn/NMJYd9Opk5POdqQV.ovpn"},"method":"chacha20-ietf-poly1305","udpdns":false,"gateway":"103.76.180.6","timeout":300,"password":"3c0a0bafba80654b9d610e11542e41862be6a2bb4711856f4b58ea37e177c101","dns_server":["8.8.8.8","8.8.4.4"],"ov_enabled":true,"ss_enabled":true,"server":["120.77.176.253","47.112.154.111","120.25.157.144","120.79.73.157"],"kcp":false,"retry_interval_time":900,"usernameForValidate":"4839479-4729-34399-9210@PANDA","passwordForValidate":"eyJleHAiOjE2OTUxMDQ5MzIsInVzZXJJZCI6Mjc5OSwiZGV2aWNlSWQiOjIyNDkzMzMwOTk3MjQ4LCJpYXQiOjE2MDg3MDQ5MzJ9.qPKNiGnLaE01ES16EbFvIO_fwi4hFKRiHDxUSUTlV-Q","retry_times":10000,"server_port":2700}

        String raw ="HfLCVcZTDz3mPpjiMIkc0aEVrLHRQRArKSf4jDtNNPXzyBXUVbrF5hiueyjULLRJLnFZwm5DNxiS80OQqBZYfI9UZgy1ELyuuz7tOVvDMQkJADTywVGwCgGs+cJtY1t1USOyTxPDilvbTGDiU/fg8P8JnNW73gM6aGiwKBrjyrbuSCzPNRGmkPEUE6ILH6MX+iWiFe52pc9xhP6o7hj1vneXx8BmTnwANdzuw25PMz/ARf/aoaPGawaPPDeqOaNry+S+JeJNiQXRTmakfEXhaLzNY5zotCR7zBzc10JDE2SeP2TPvOnVXykSADrUDWJVZHFS+w9/08/mTVzm4+J1H0Dcr+QEi1lasdDnJNHEPg5ScZjh9K2TKCrX1pTNR6q/x7DxX9KNDuKej0fCzR/dgLYhLEr5KK8Tb/6NU7NUBUm1TYVWOKZq1s2gWlfCdo4ioG6FmbP02ZyRFW3aEevWKpWhFkCymSRnD3Z74lwxZrkfOIXDH3GY/kjfyLQEbYeMgqFFFI8oCVG84EIWFc4BhQEhX1qEojW8/Tfq3RGHIG6ZF4NyA0jxgrxwHJiNy4lWaxfTKHGvMLwLYC3QGvy9OKJMSMKoe8ztXvVkLn9jDvwJ4BPxErcgc8eVeyXO0P+zZT+UVnLjUwL02kDpJzuwq8YKceuoYNgpm/djSxsK4DHyphe97ubwt7ZoJ5ue/2tqm9DnsGIhxcQ1fVLOqAvBOXEBmd3H/P+hjX+xHy6uGB+PSkjonqOWTPELdAnM75efSCWmLYUp+Qr65I8T+WkseQ==";
        System.out.println(aesDemo.decode(raw));
    }

    public String encode(String plainText) {
        try {
            cipher.init(ENCRYPT_MODE, secretKeySpec);
            byte[] encode = base64.encode(cipher.doFinal(plainText.getBytes(UTF_8)));
            return new String(encode, UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("encode failed", e);
        }
    }

    public String decode(String cipherText) {
        try {
            cipher.init(DECRYPT_MODE, secretKeySpec);
            return new String(cipher.doFinal(base64.decode(cipherText)), UTF_8);
        } catch (Exception e) {
            throw new RuntimeException("decode failed", e);
        }
    }

    public void init() throws Exception {
        cipher = Cipher.getInstance(AES);
        secretKeySpec = new SecretKeySpec(secretKey.getBytes(UTF_8), AES);
        base64 = new Base64();
    }
}
