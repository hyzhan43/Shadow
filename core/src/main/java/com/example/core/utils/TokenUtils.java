package com.example.core.utils;

import com.example.core.bean.card.TokenCard;
import com.example.core.config.Secure;
import com.example.core.error.BaseException;
import com.example.core.error.code.ErrorCode;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.servlet.http.HttpServletRequest;

/**
 * author：  HyZhan
 * create：  2019/4/17
 * desc：    TODO
 */
public class TokenUtils {

    public static String uid;

    public static String parseToken(String token) {

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

    public static void verifyToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty()) {
            throw new BaseException(ErrorCode.TOKEN_EMPTY);
        }

        if (!token.contains("Bearer ")){
            throw new BaseException(ErrorCode.TOKEN_ERROR);
        }

        token = token.substring(7);
        uid = TokenUtils.parseToken(token);

        if (uid == null || uid.isEmpty()) {
            throw new BaseException(ErrorCode.TOKEN_ERROR);
        }

        request.setAttribute("uid", uid);
    }
}