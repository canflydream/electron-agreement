package org.cfd.electron_agreement.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.cfd.electron_agreement.beans.po.User;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtils {
    private static String templatePath = System.getProperty("tlPath");

    public static String generateUuid() {
        return UUID.randomUUID().toString();
    }

    public static String token(HttpServletRequest request) {
        return request.getHeader("token");
    }

    public static String getLoginIdFromRequest(HttpServletRequest request) {
        return JWTUtils.getLoginId(token(request));
    }

    public static String getExcelDir(User user) {
        String path = templatePath + user.getModule() + File.separator + "excel" + File.separator;
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
        return path;
    }

    public static String getSealDir(User user) {
        String path = templatePath + user.getModule() + File.separator + "seal" + File.separator;
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
        return path;
    }

    public static String getHtmlDir(User user) {
        String path = templatePath + user.getModule() + File.separator + "html" + File.separator;
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
        return path;
    }

    public static String randomFileName(String title) {
        StringBuilder sb = new StringBuilder();
        sb.append(System.currentTimeMillis() / 1000)
                .append("_")
                .append(title)
                .append("_")
                .append(RandomStringUtils.randomAlphabetic(8));
        return sb.toString();
    }

    private static String pattern = "\\{\\{([^}]*)\\}\\}";
    private static Pattern r = Pattern.compile(pattern);

    public static String elementDataReplaceEmpty(String content) {
        Matcher matcher = r.matcher(content);
        List<String> list = new ArrayList<>();
        List<String> list2 = new ArrayList<>();

        while (matcher.find()) {
            list.add(matcher.group());
            list2.add("");
        }
        return StringUtils.replaceEach(content, ArrayUtils.toStringArray(list.toArray()),
                ArrayUtils.toStringArray(list2.toArray()));
    }

    public static String elementDataReplace(String content, String jsonData) {
        Matcher matcher = r.matcher(content);
        JSONObject jsonObject = JSONObject.parseObject(jsonData);

        List<String> list = new ArrayList<>();
        List<String> list2 = new ArrayList<>();

        while (matcher.find()) {
            String replaceKey = matcher.group();
            String dataKey = matcher.group(1);
            list.add(replaceKey);
            list2.add(jsonObject.getString(dataKey));
        }
        return StringUtils.replaceEach(content, ArrayUtils.toStringArray(list.toArray()),
                ArrayUtils.toStringArray(list2.toArray()));
    }
}
