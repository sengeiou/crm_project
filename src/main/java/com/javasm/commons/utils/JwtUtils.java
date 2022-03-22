package com.javasm.commons.utils;

import com.javasm.commons.config.BootProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import jdk.nashorn.internal.objects.annotations.Constructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author:
 * @className:JwtUtils
 * @description:token生成工具类
 * @date:2022/2/7 21:12
 * @version:0.1
 * @since:1.8
 */
@Component
public class JwtUtils {

    private static Long expTime ;
    private static String key ;
    private static String customClaimKey = "UID";


    @Resource
     private BootProperties bp;


    @PostConstruct
    public  void setTokenInfo(){
        expTime = bp.getTokenInfo().getExpTime();
        key=bp.getTokenInfo().getKey();
        //key=key();
    }

    //创建一个符合算法要求的秘钥
    public static String key() {
        Key key = io.jsonwebtoken.security.Keys.secretKeyFor(SignatureAlgorithm.HS256);
        String secretString = Encoders.BASE64.encode(key.getEncoded());
        System.out.println(secretString);
        return secretString;
    }

    public static String generate(String u) {
        Map<String, String> claim = new HashMap<>();
        claim.put(customClaimKey, u);
        Date current = new Date();
        Date expDate = new Date(current.getTime() + expTime);
        Key k = io.jsonwebtoken.security.Keys.hmacShaKeyFor(Decoders.BASE64.decode(key));
        String jws = Jwts.builder()
                .setClaims(claim)//向body中写入自定义明文签名信息
                .setIssuedAt(current)//签发时间：向body中写入的明文标准签名信息
                .setExpiration(expDate)//过期时间
                .signWith(k)//指定秘钥
                .compact();//加密字符串
        return jws;
    }

    public static Claims parse(String jwt) {
        Jws<Claims> jws;
        try {
            jws = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(jwt);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return jws.getBody();
    }

    public static String getUid(Claims c) {
        Object o = c.get(customClaimKey);
        return (String)o;
    }







}
