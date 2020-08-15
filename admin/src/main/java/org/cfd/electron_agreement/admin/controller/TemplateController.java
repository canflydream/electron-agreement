package org.cfd.electron_agreement.admin.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.Charsets;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.cfd.electron_agreement.admin.service.ITemplateService;
import org.cfd.electron_agreement.admin.service.IUserService;
import org.cfd.electron_agreement.beans.po.User;
import org.cfd.electron_agreement.exception.ApiException;
import org.cfd.electron_agreement.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/admin/tl")
@Slf4j
public class TemplateController {
    private String tmpPath = System.getProperty("tlPath");

    private final ITemplateService templateService;
    private final IUserService userService;

    @Autowired
    public TemplateController(ITemplateService templateService, IUserService userService) {
        this.templateService = templateService;
        this.userService = userService;
    }

    @PostMapping("upload")
    public Object upload(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            throw new ApiException(-1, "miss file");
        }
        User user = userService.findUserFromRequest(request);

        String excelDir = CommonUtils.getExcelDir(user);

        String destFileName = excelDir + CommonUtils.randomFileName(FilenameUtils.getBaseName(file.getOriginalFilename())) + ".excel";
        File saveFile = new File(destFileName);

        try {
            file.transferTo(saveFile);
            templateService.uploadTl(user, destFileName);
            return true;
        } catch (IOException e) {

            log.error(e.getMessage(), e);
        }
        return false;
    }

    @PostMapping("/save")
    public Object htmlSave(HttpServletRequest request, @RequestBody HtmlContentVo html) {
        User user = userService.findUserFromRequest(request);
        String htmlDir = CommonUtils.getHtmlDir(user);
        String fileName = htmlDir + CommonUtils.randomFileName(html.getTitle()) + ".html";
        String showFile = htmlDir + CommonUtils.randomFileName(html.getTitle()) + "_show.html";
        File file = new File(fileName);
        File showFile2 = new File(showFile);
        try {
            String content = html.getContent();
            FileUtils.writeStringToFile(file, content, Charsets.UTF_8);
            String str = CommonUtils.elementDataReplaceEmpty(content);
            FileUtils.writeStringToFile(showFile2, str, Charsets.UTF_8);
            templateService.uploadTl(user, fileName);
            return true;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new ApiException(-1, e.getMessage());
        }
    }


    @PostMapping("/preview")
    public Object preview(@RequestBody HtmlContentVo html) {
        String s = CommonUtils.generateUuid();
        String t = tmpPath + s + ".html";
        File file = new File(t);
        try {
            String content = html.getContent();
            String str = CommonUtils.elementDataReplaceEmpty(content);
            FileUtils.writeStringToFile(file, str, Charsets.UTF_8);
            return s;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new ApiException(-1, e.getMessage());
        }
    }

    @PostMapping("/preview/data")
    public Object previewData(@RequestBody HtmlContentVo html) {
        String s = CommonUtils.generateUuid();
        String t = tmpPath + s + ".html";
        File file = new File(t);
        try {
            String content = html.getContent();
            String str = CommonUtils.elementDataReplace(content, html.getJsonData().toJSONString());
            FileUtils.writeStringToFile(file, str, Charsets.UTF_8);
            return s;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new ApiException(-1, e.getMessage());
        }
    }
}

@Data
class HtmlContentVo {
    private String title;
    private String content;
    private JSONObject jsonData;
}
