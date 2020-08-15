package org.cfd.electron_agreement.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

public class JWTUtils {

    public static String getToken(String loginId,String privateKey){
        return JWT.create().withAudience(loginId)
                .sign(Algorithm.HMAC256(privateKey));
    }

    public static String getLoginId(String token){
        return JWT.decode(token).getAudience().get(0);
    }

    public static String getPrivateKey(String token){
        return JWT.decode(token).getAudience().get(1);
    }
}
