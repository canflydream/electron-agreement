package org.cfd.electron_agreement.auth.service.impl;

import org.apache.commons.lang3.RandomStringUtils;
import org.cfd.electron_agreement.auth.service.IUserService;
import org.cfd.electron_agreement.beans.constants.CollectionNames;
import org.cfd.electron_agreement.beans.po.User;
import org.cfd.electron_agreement.beans.vo.ApplyVO;
import org.cfd.electron_agreement.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {
    private final MongoTemplate mongoTemplate;

    @Autowired
    public UserServiceImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    @Override
    public User findUserByLoginId(String loginId) {
        Query query = new Query(Criteria.where("loginId").is(loginId));
        return mongoTemplate.findOne(query,User.class);
    }

    @Override
    public User insert(ApplyVO applyVO) {
        User user = User.builder()
                .id(UUID.randomUUID().toString())
                .loginId(RandomStringUtils.randomAlphanumeric(6))
                .privateKey(UUID.randomUUID().toString())
                .company(applyVO.getCompany())
                .userName(applyVO.getUserName())
                .module(applyVO.getModule())
                .createTime(new Date())
                .build();
        mongoTemplate.insert(user, CollectionNames.USER);
        return user;
    }

    @Override
    public User findUserFromRequest(HttpServletRequest request) {
        String loginId = CommonUtils.getLoginIdFromRequest(request);
        return findUserByLoginId(loginId);
    }
}
