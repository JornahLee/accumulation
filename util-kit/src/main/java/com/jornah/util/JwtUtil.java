package com.jornah.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public final class JwtUtil {
    private static final String SECRET_KEY = "this is secret key";


    public static void main(String[] args) throws InterruptedException {
        testJwt();
    }

    private static void testJwt() {
        Map<String, Object> payload = new HashMap<>();
        payload.put("username", "zhangsan");
        payload.put("id", "14");
        payload.put("maxInteger",Integer.MAX_VALUE+1L);

        // HashMap<Object, Object> other = new HashMap<>();
        // other.put(2,3);
        // other.put(3,3);

        String token = generateToken(payload, Instant.now().plusSeconds(10));
        parseToken(token).forEach(entry-> System.out.println("--licg---     entry : " + entry + "    -----"));
        // boolean tokenValid = isTokenValid(token);
        // System.out.println("--licg---     token : " + token + "    -----");
        // System.out.println("--licg---     parseToken(token) : " + parseToken(token) + "    -----");
        // System.out.println("--licg---     tokenValid : " + tokenValid + "    -----");
        // Thread.sleep(TimeUnit.SECONDS.toMillis(2));
    }

    public static String generateToken(Map<String, Object> payload, Instant expiration) {
        return Jwts.builder()
                .setClaims(payload)
                .setExpiration(new Date(expiration.toEpochMilli()))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();

    }
    public static String generateToken(String payload, Instant expiration) {
        return Jwts.builder()
                .setSubject(payload)
                .setIssuedAt(new Date(Instant.now().toEpochMilli()))
                .setExpiration(new Date(expiration.toEpochMilli()))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();

    }


    public static Set<Map.Entry<String, Object>> parseToken(String jwt) {
        Jws<Claims> claimsJws = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(jwt);
        // 这里有坑
        // 如果从token解析出的payload的 value是数字，他会自动转成 对应范围的 integer或者long
        // 建议先转成字符串，再进行统一转换
        System.out.println("--licg---     hearder  << -----");
        claimsJws.getHeader().entrySet().forEach(System.out::println);
        System.out.println("--licg---     hearder  >> -----");

        System.out.println("--licg---     body  << -----");
        claimsJws.getBody().entrySet().forEach(System.out::println);
        System.out.println("--licg---     body  >> -----");

        return claimsJws.getBody().entrySet();

    }

    public static boolean isTokenValid(String jwt) {
        try {
            parseToken(jwt);
        } catch (Throwable e) {
            return false;
        }
        return true;
    }
    public static String test(@NotNull String jwt) {
        // 要配上注解处理器才有用
        System.out.println("--licg---     1 : " + 1 + "    -----");
        return jwt.substring(0,1);
    }

}
