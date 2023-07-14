package com.example.softunispringjson.service;


import com.example.softunispringjson.model.entity.User;

import java.io.IOException;

public interface UserService {
    void seedUsers() throws IOException;
    User findRandomUser();
}
