package org.cfd.electron_agreement.auth;

import org.apache.commons.lang3.RandomStringUtils;
import org.cfd.electron_agreement.beans.constants.CollectionNames;
import org.cfd.electron_agreement.beans.po.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ElectronTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void find(){
        Query query = new Query(Criteria.where("loginId").is("Rq05wa"));
        User one = mongoTemplate.findOne(query, User.class);
        System.out.println(one);
    }

    @Test
    public void addUser(){
        User user = User.builder()
                .id(UUID.randomUUID().toString())
                .loginId(RandomStringUtils.randomAlphanumeric(6))
                .privateKey(UUID.randomUUID().toString())
                .company("youlu")
                .userName("liwentao")
                .module("test")
                .createTime(new Date())
                .build();
        mongoTemplate.insert(user, CollectionNames.USER);
    }
}
