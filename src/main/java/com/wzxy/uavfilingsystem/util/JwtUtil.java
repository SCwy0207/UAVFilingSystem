package com.wzxy.uavfilingsystem.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

public class JwtUtil {
    // 生成一个安全的密钥 (HS256需要至少256位)
    public static final SecretKey signKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // 过期时间(1天), 通常设置为30分钟
    private static final Long expire = 60 * 60 * 24 * 1000L;

    /**
     * 生成JWT令牌
     * @param claims JWT第二部分负载 payload 中存储的内容
     * @return 生成的JWT字符串
     */
    public static String generateJwt(Map<String, Object> claims){
        String jwt = Jwts.builder()
                // 自定义信息（有效载荷）
                .addClaims(claims)
                // 设置过期时间
                .setExpiration(new Date(System.currentTimeMillis() + expire))
                // 使用生成的安全密钥进行签名
                .signWith(signKey)
                .compact();
        return jwt;
    }

    /**
     * 解析JWT令牌
     * @param jwt JWT令牌
     * @return JWT第二部分负载 payload 中存储的内容
     */
    public static Claims parseJWT(String jwt){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(signKey)
                .build()
                .parseClaimsJws(jwt)
                .getBody();
        return claims;
    }

    /**
     * 获取用户ID
     * @param token JWT令牌
     * @return 从token中提取的用户ID
     */
    public static String getUserId(String token){
        Claims claims = parseJWT(token);
        String id = String.valueOf(claims.get("userId"));
        return id;
    }
}
