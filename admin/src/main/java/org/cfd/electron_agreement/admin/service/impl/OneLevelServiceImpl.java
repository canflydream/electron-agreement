package org.cfd.electron_agreement.admin.service.impl;

import org.cfd.electron_agreement.admin.service.IOneLevelService;
import org.cfd.electron_agreement.beans.constants.CollectionNames;
import org.cfd.electron_agreement.beans.po.OneLevelElement;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OneLevelServiceImpl implements IOneLevelService {
    private final MongoTemplate mongoTemplate;

    public OneLevelServiceImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<OneLevelElement> getList(String loginId) {
        return mongoTemplate.find(new Query(Criteria.where("loginId").is(loginId)), OneLevelElement.class,
                CollectionNames.ONE_LEVEL_ELEMENT);
    }

    @Override
    public void save(String loginId, String data) {
        OneLevelElement build = OneLevelElement.builder().loginId(loginId).data(data).build();
        mongoTemplate.save(build, CollectionNames.ONE_LEVEL_ELEMENT);
    }
}
