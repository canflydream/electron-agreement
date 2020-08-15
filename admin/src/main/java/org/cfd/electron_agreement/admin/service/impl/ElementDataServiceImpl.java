package org.cfd.electron_agreement.admin.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.cfd.electron_agreement.admin.service.IElementDataService;
import org.cfd.electron_agreement.beans.constants.CollectionNames;
import org.cfd.electron_agreement.beans.po.ElementData;
import org.cfd.electron_agreement.beans.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ElementDataServiceImpl implements IElementDataService {
    private final MongoTemplate mongoTemplate;

    @Autowired
    public ElementDataServiceImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void save(User user, String body) {
        ElementData elementData = ElementData.builder().userId(user.getId())
                .createTime(new Date())
                .data(body)
                .build();
        mongoTemplate.insert(elementData, CollectionNames.ELEMENT_DATA);
    }

    @Override
    public JSONArray getListByUserId(String id) {
        ElementData elementData = mongoTemplate.findOne(new Query(Criteria.where("userId").is(id)), ElementData.class, CollectionNames.ELEMENT_DATA);
        return elementData == null ? new JSONArray() : JSONArray.parseArray(elementData.getData());
    }
}
