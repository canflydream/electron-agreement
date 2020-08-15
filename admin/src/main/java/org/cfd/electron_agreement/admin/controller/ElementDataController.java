package org.cfd.electron_agreement.admin.controller;

import lombok.extern.slf4j.Slf4j;
import org.cfd.electron_agreement.admin.service.IElementDataService;
import org.cfd.electron_agreement.admin.service.IUserService;
import org.cfd.electron_agreement.beans.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/admin/data")
@Slf4j
public class ElementDataController {
    private final IElementDataService elementDataService;
    private final IUserService userService;

    @Autowired
    public ElementDataController(IElementDataService elementDataService, IUserService userService) {
        this.elementDataService = elementDataService;
        this.userService = userService;
    }

    @PostMapping("save")
    public Object save(@RequestBody String body, HttpServletRequest request) {
        User user = userService.findUserFromRequest(request);
        elementDataService.save(user, body);
        return true;
    }

    @GetMapping("get")
    public Object get(HttpServletRequest request){
        User user = userService.findUserFromRequest(request);
        return elementDataService.getListByUserId(user.getId());
    }
}
