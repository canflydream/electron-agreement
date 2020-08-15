package org.cfd.electron_agreement.auth.service.impl;

import org.cfd.electron_agreement.auth.service.ITlService;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class TlServiceImpl implements ITlService {
    private final MongoTemplate mongoTemplate;

    public TlServiceImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
}
