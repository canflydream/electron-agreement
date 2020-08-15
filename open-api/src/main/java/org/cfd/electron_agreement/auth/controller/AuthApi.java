package org.cfd.electron_agreement.auth.controller;

import org.cfd.electron_agreement.annotation.PassToken;
import org.cfd.electron_agreement.utils.JWTUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthApi {

    @GetMapping("token")
    @PassToken
    public Object token(String loginId,String privateKey){
        return JWTUtils.getToken(loginId,privateKey);
    }


}
