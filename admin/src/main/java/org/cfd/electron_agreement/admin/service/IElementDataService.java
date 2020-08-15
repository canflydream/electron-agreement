package org.cfd.electron_agreement.admin.service;

import com.alibaba.fastjson.JSONArray;
import org.cfd.electron_agreement.beans.po.User;

import java.util.Map;

public interface IElementDataService {
    void save(User user, String body);

    JSONArray getListByUserId(String id);
}
