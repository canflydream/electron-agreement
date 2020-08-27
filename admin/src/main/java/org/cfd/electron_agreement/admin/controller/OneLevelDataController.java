package org.cfd.electron_agreement.admin.controller;

import com.alibaba.fastjson.JSONObject;
import org.cfd.electron_agreement.admin.service.IOneLevelService;
import org.cfd.electron_agreement.utils.CommonUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/admin/one-level")
public class OneLevelDataController {
    private final IOneLevelService oneLevelService;

    public OneLevelDataController(IOneLevelService oneLevelService) {
        this.oneLevelService = oneLevelService;
    }

    @GetMapping("list")
    public Object list(HttpServletRequest request) {
        return oneLevelService.getList(CommonUtils.getLoginIdFromRequest(request));
    }

    @PostMapping("save")
    public Object save(@RequestBody JSONObject data, HttpServletRequest request) {
        String loginId = CommonUtils.getLoginIdFromRequest(request);
        oneLevelService.save(loginId,data.getString("data"));
        return true;
    }
}
