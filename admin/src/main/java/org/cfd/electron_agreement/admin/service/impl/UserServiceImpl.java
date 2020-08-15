package org.cfd.electron_agreement.admin.service.impl;

import org.cfd.electron_agreement.admin.service.IUserService;
import org.cfd.electron_agreement.beans.constants.CollectionNames;
import org.cfd.electron_agreement.beans.po.User;
import org.cfd.electron_agreement.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class UserServiceImpl implements IUserService {
    private final MongoTemplate mongoTemplate;

    @Autowired
    public UserServiceImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public User findUserByLoginId(String loginId) {
        return mongoTemplate.findOne(new Query(Criteria.where("loginId").is(loginId)),
                User.class, CollectionNames.USER);
    }

    @Override
    public User findUserFromRequest(HttpServletRequest request) {
        String loginId = CommonUtils.getLoginIdFromRequest(request);
        return findUserByLoginId(loginId);
    }
}
