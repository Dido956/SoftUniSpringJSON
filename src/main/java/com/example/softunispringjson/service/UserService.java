package com.example.softunispringjson.service;


import com.example.softunispringjson.model.dto.UserSoldDto;
import com.example.softunispringjson.model.entity.User;

import java.io.IOException;
import java.util.List;

public interface UserService {
    void seedUsers() throws IOException;
    User findRandomUser();

    List<UserSoldDto> findAllUserWithMoreThanOneSoldProduct();
}

