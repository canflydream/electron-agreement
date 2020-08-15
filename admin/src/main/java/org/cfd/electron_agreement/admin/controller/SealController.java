package org.cfd.electron_agreement.admin.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.cfd.electron_agreement.admin.service.ISealService;
import org.cfd.electron_agreement.admin.service.IUserService;
import org.cfd.electron_agreement.beans.po.User;
import org.cfd.electron_agreement.exception.ApiException;
import org.cfd.electron_agreement.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/admin/seal")
public class SealController {
    private final ISealService sealService;
    private final IUserService userService;

    @Autowired
    public SealController(ISealService sealService, IUserService userService) {
        this.sealService = sealService;
        this.userService = userService;
    }

    @PostMapping("upload")
    public Object uploadSeal(MultipartFile file, HttpServletRequest request){
        if(file.isEmpty()){
            throw new ApiException(-1,"miss file");
        }

        User user = userService.findUserFromRequest(request);

        String excelDir = CommonUtils.getSealDir(user);

        String destFileName = excelDir + CommonUtils.randomFileName(FilenameUtils.getBaseName(file.getOriginalFilename()))+".png";
        File saveFile = new File(destFileName);

        try {
            file.transferTo(saveFile);
            sealService.upload(user, destFileName);
            return true;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return false;
    }
}
