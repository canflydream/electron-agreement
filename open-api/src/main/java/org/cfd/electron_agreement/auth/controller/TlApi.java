package org.cfd.electron_agreement.auth.controller;

import org.cfd.electron_agreement.auth.service.ITlService;
import org.cfd.electron_agreement.auth.service.IUserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/tl")
public class TlApi {
    private final ITlService tlService;
    private final IUserService userService;

    public TlApi(ITlService tlService, IUserService userService) {
        this.tlService = tlService;
        this.userService = userService;
    }

    @PostMapping("sign")
    public Object sign(HttpServletRequest request) {
        userService.findUserFromRequest(request);
        return true;
    }


}
