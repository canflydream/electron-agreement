package org.cfd.electron_agreement.admin.service.impl;

import org.cfd.electron_agreement.admin.service.ISealService;
import org.cfd.electron_agreement.beans.constants.CollectionNames;
import org.cfd.electron_agreement.beans.po.Seal;
import org.cfd.electron_agreement.beans.po.User;
import org.cfd.electron_agreement.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SealServiceImpl implements ISealService {
    private final MongoTemplate mongoTemplate;

    @Autowired
    public SealServiceImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void upload(User user, String destFileName) {
        Seal seal = Seal.builder()
                .id(CommonUtils.generateUuid())
                .userId(user.getId())
                .createTime(new Date())
                .path(destFileName)
                .build();
        mongoTemplate.insert(seal, CollectionNames.SEAL);
    }
}
