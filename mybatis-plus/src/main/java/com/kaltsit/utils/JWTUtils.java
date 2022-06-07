//package com.kaltsit.utils;
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.JWTCreator;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.auth0.jwt.interfaces.DecodedJWT;
//
//import java.util.Calendar;
//import java.util.Map;
//
//public class JWTUtils {
//
//    //秘钥
//    private static final String TOKEN = "xpp@ll";
//
//    /**
//     * 生成token
//     * @param map 用户传过了的信息，例如id等
//     * @return token
//     */
//    public static String createToken(Map<String, String> map) {
//        Calendar instance = Calendar.getInstance();
//        //设置默认7天过期
//        instance.add(Calendar.DATE, 7);
//        //创建jwt builder
//        JWTCreator.Builder builder = JWT.create();
//        //payload
//        map.forEach(builder::withClaim);
//        //指定令牌过期时间
//        String token = builder.withExpiresAt(instance.getTime())
//                //sign，传入加密盐
//                .sign(Algorithm.HMAC256(TOKEN));
//        return token;
//    }
//
//    /**
//     * 验证token合法性
//     *
//     * @param token
//     */
//    public static void verifyToken(String token) {
//        JWT.require(Algorithm.HMAC256(TOKEN)).build().verify(token);
//    }
//
//    /**
//     * 获取token信息方法
//     *
//     * @param token
//     * @return
//     */
//    public static DecodedJWT getTokenInfo(String token) {
//        DecodedJWT verify = JWT.require(Algorithm.HMAC256(TOKEN)).build().verify(token);
//        return verify;
//    }
//
//}
//
