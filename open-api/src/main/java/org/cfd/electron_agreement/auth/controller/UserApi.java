package org.cfd.electron_agreement.auth.controller;

import org.cfd.electron_agreement.auth.service.IUserService;
import org.cfd.electron_agreement.beans.po.User;
import org.cfd.electron_agreement.beans.vo.ApplyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserApi {
    private final IUserService userService;

    @Autowired
    public UserApi(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("apply")
    public User apply(@RequestBody ApplyVO applyVO){
        return userService.insert(applyVO);
    }
}
