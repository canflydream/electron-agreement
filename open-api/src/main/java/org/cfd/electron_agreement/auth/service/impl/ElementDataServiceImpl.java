package org.cfd.electron_agreement.auth.service.impl;

import org.cfd.electron_agreement.auth.service.IElementDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class ElementDataServiceImpl implements IElementDataService {
    private final MongoTemplate mongoTemplate;

    @Autowired
    public ElementDataServiceImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


}
