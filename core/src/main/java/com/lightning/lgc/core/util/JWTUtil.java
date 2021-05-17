package com.lightning.lgc.core.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;

@Slf4j
public class JWTUtil {
    private final static int EXPIRE_TIME = 7;
    private final static String SECRET = "*******";
    private final static Algorithm ALGORITHM = Algorithm.HMAC256(SECRET);

    public static String createToken(String userId, String username) {
        //设置Token过期时间
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, EXPIRE_TIME);

        return JWT.create()
                .withClaim("userId", userId)
                .withClaim("username", username)
                .withExpiresAt(calendar.getTime())
                .sign(ALGORITHM);
    }

    public static String createToken(String userId, String username,String userNickname) {
        //设置Token过期时间
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, EXPIRE_TIME);

        return JWT.create()
                .withClaim("userId", userId)
                .withClaim("username", username)
                .withClaim("userNickname",userNickname)
                .withExpiresAt(calendar.getTime())
                .sign(ALGORITHM);
    }

    public static String getUserId(String token) {
        return JWT.require(ALGORITHM).build().verify(token).getClaim("userId").toString();
    }

    public static String getUsername(String token) {
        return JWT.require(ALGORITHM).build().verify(token).getClaim("username").asString();
    }

    public static boolean verify(String token, String userId, String username) throws Exception {
        JWT.require(ALGORITHM).withClaim("userId", userId).withClaim("username", username).build().verify(token);
        return true;
    }

    public static void verify(String token) throws Exception {
        JWT.require(ALGORITHM).build().verify(token);
    }

    public static String getToken(ServletRequest request) {
        HttpServletRequest req = (HttpServletRequest) request;
        return req.getHeader("token");
    }
}
