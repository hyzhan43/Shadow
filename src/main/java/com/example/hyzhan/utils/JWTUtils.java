package com.example.hyzhan.utils;

import com.example.hyzhan.config.Secure;
import com.example.hyzhan.config.Setting;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * author：  HyZhan
 * create：  2019/4/17
 * desc：    TODO
 */
public class JWTUtils {

    public static String parseJwt(String token) {

        return Jwts.parser()
                .setSigningKey(Secure.SECRET)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public static String createToken(String uid, int time) {

        return Jwts.builder()
                .setSubject(uid)
                .signWith(SignatureAlgorithm.HS512, Secure.SECRET)
                .setExpiration(DateUtils.convertTime(time))
                .compact();
    }
}