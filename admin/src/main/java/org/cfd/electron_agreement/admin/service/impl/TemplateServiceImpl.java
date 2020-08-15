package org.cfd.electron_agreement.admin.service.impl;

import org.cfd.electron_agreement.admin.service.ITemplateService;
import org.cfd.electron_agreement.beans.constants.CollectionNames;
import org.cfd.electron_agreement.beans.po.User;
import org.cfd.electron_agreement.beans.po.AgreementTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class TemplateServiceImpl implements ITemplateService {
    private final MongoTemplate mongoTemplate;

    @Autowired
    public TemplateServiceImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void uploadTl(User user, String path) {
        AgreementTemplate agreementTemplate = AgreementTemplate.builder().id(UUID.randomUUID().toString())
                .path(path)
                .userId(user.getId())
                .createTime(new Date())
                .build();
        mongoTemplate.insert(agreementTemplate, CollectionNames.AGREEMENT_TEMPLATE);
    }
}
