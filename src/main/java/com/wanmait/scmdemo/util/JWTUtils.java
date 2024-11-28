package com.wanmait.scmdemo.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class JWTUtils {

    /**
     *
     * @param audience 签发对象，可以是用户id username name或者固定的字符串
     * @param expireMinutes 过期时间，单位分钟
     * @param secret 密钥，可以是用户的密码，或者一个复杂的固定的字符串
     * @param claims 载荷
     * @return token
     */
    public static String createToken(String audience, int expireMinutes, String secret, Map<String,String> claims) {

        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.MINUTE,expireMinutes);
        Date expiresDate = nowTime.getTime();

        JWTCreator.Builder builder =  JWT.create()
                .withAudience(audience)   //签发对象，可以是固定的字符串，或者是用户的id或者用户名
                .withIssuedAt(new Date())    //发行时间
                .withExpiresAt(expiresDate);  //有效时间
      /*  claims.forEach((key,value)->{
            builder.withClaim(key,value);
        });*/
        builder.withPayload(claims);//载荷
        return builder.sign(Algorithm.HMAC256(secret));   //签名 加密，密钥可以用很复杂的一串字符串或者是用户的密码
    }

    /*
     签发对象：用户的id
     签发时间：现在
     有效时间：60分钟
     载荷内容：用户名和姓名，还可以是其它任何信息
     加密密钥：用户的密码
     */
  /*  public static String createToken(Admin admin) {

        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.MINUTE,60);
        Date expiresDate = nowTime.getTime();

        return JWT.create()
                .withAudience(admin.getId().toString())   //签发对象
                .withIssuedAt(new Date())    //发行时间
                .withExpiresAt(expiresDate)  //有效时间
                .withClaim("username", admin.getUsername())//载荷
                .withClaim("name", admin.getName())//载荷
                .sign(Algorithm.HMAC256(admin.getPassword()));   //加密
    }*/

    //检验合法性，其中secret为用户的密码
    public static void verifyToken(String token, String secret)
    {
        DecodedJWT jwt = null;
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret)).build();
            jwt = verifier.verify(token);

        } catch (Exception e) {
            //效验失败
            throw new JWTVerificationException("token校验失败，非法认证信息");

        }

    }

   //获取签发对象也就是用户的id
    public static String getAudience(String token) {
        String audience=null;

        try {
            audience = JWT.decode(token).getAudience().get(0);

        } catch (JWTDecodeException j) {
            //这里是token解析失败
            throw new JWTDecodeException("token解析失败，非法认证信息");
        }
        return audience;
    }


    //通过载荷名字获取载荷的值
    public static String getClaimByName(String token, String name){
        Claim claim = JWT.decode(token).getClaim(name);
        if(claim!=null){
            return claim.asString();
        }
        return null;
    }
}
