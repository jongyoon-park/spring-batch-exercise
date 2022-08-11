package com.example.batchexample;

import com.example.batchexample.service.entity.UserInfo;
import com.example.batchexample.repository.UserInfoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class BatchexampleApplicationTests {

    @Autowired
    UserInfoRepository userInfoRepository;

    @Test
    void cleanUp() {
        System.out.println("start clean up");
        userInfoRepository.deleteAll();
        System.out.println("terminate clean up");
    }

    @Test
    void getUserTest() {
        System.out.println("start get user");
        List<UserInfo> userList = userInfoRepository.findAll();
        userList.stream().forEach(userInfo -> System.out.println(userInfo.toString()));
        System.out.println("terminate get user");
    }

    @Test
    void insertTest() {
        System.out.println("start insert test");
        UserInfo tempUser = UserInfo.builder().name("윤종").email("cleanbook@naver.com").build();
        userInfoRepository.save(tempUser);
        System.out.println("terminate insert test");
    }

    @Test
    void contextLoads() {
        System.out.println("test");
    }

}
