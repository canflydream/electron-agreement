package org.cfd.electron_agreement.admin.controller;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.cfd.electron_agreement.admin.service.IUserService;
import org.cfd.electron_agreement.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/login")
@Slf4j
public class LoginController {
    private final IUserService userService;

    @Autowired
    public LoginController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/check")
    public Object check(@RequestBody LoginCheckVO vo) {
        return JWTUtils.getToken(vo.getLoginId(), vo.getPrivateKey());
    }


}

@Data
@ToString
class LoginCheckVO {
    private String loginId;
    private String privateKey;
}
