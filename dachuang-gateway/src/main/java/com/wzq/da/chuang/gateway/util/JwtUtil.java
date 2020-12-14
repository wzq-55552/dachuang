package com.wzq.da.chuang.gateway.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;

/**
 * Title：JJWT生成令牌工具类
 * Description：
 * @author WZQ
 * @version 1.0.0
 * @date 2020/3/10
 */
public class JwtUtil {
    //默认有效期
    public static final Long JWT_TTL = 3600000L;// 60 * 60 *1000  一个小时

    //Jwt令牌信息
    public static final String JWT_KEY = "itcast";

    /**
     * 生成令牌
     * @param id 唯一标识
     * @param subject 主题
     * @param ttlMillis 令牌过期时间
     * @return
     */
    public static String createJWT(String id, String subject, Long ttlMillis) {
        //指定算法
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        //当前系统时间
        long nowMillis = System.currentTimeMillis();
        //令牌签发时间
        Date now = new Date(nowMillis);

        //如果令牌有效期为null，则默认设置有效期1小时
        if (ttlMillis == null) {
            ttlMillis = JwtUtil.JWT_TTL;
        }

        //令牌过期时间设置
        long expMillis = nowMillis + ttlMillis;
        Date expDate = new Date(expMillis);

        //生成秘钥
        SecretKey secretKey = generalKey();

        //封装Jwt令牌信息
        JwtBuilder builder = Jwts.builder()
                .setId(id)                    //唯一的ID
                .setSubject(subject)          // 主题  可以是JSON数据
                .setIssuer("admin")          // 签发者
                .setIssuedAt(now)             // 签发时间
                .signWith(signatureAlgorithm, secretKey) // 签名算法以及密匙
                .setExpiration(expDate);      // 设置过期时间
        return builder.compact();
    }

    /**
     * 生成加密 secretKey
     * 盐base64加密成新的盐
     * @return
     */
    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.getEncoder().encode(JwtUtil.JWT_KEY.getBytes());
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }


    /**
     * 解析令牌数据
     *
     * @param jwt
     * @return
     * @throws Exception
     */
    public static Claims parseJWT(String jwt) throws Exception {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }

    public static void main(String[] args) {
        String jwt = JwtUtil.createJWT("weiyibiaoshi", "aaaaaa", null);
        System.out.println(jwt);
        try {
            Claims claims = JwtUtil.parseJWT(jwt);
            System.out.println(claims);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
