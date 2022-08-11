package com.example.batchexample.service;

import com.example.batchexample.repository.UserInfoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private UserInfoRepository userInfoRepository;

    private void batchJob() {

    }
}
