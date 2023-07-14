package com.example.softunispringjson.service.impl;

import com.example.softunispringjson.model.dto.UserSeedDto;
import com.example.softunispringjson.model.dto.UserSoldDto;
import com.example.softunispringjson.model.entity.User;
import com.example.softunispringjson.repository.UserRepository;
import com.example.softunispringjson.service.UserService;
import com.example.softunispringjson.util.ValidationUtil;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static com.example.softunispringjson.constanats.GlobalConstants.*;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private static final String USERS_FILE_NAME = "users.json";
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final Gson gson;

    @Override
    public void seedUsers() throws IOException {
        Arrays
                .stream(gson.fromJson(Files.readString(Path.of(RESOURCES_FILE_PATH + USERS_FILE_NAME)), UserSeedDto[].class))
                .filter(validationUtil::isValid)
                .map(userSeedDto -> modelMapper.map(userSeedDto, User.class))
                .forEach(userRepository::save);

    }

    @Override
    public User findRandomUser() {
        long randomId = ThreadLocalRandom
                .current()
                .nextLong(1, userRepository.count() + 1);

        return userRepository
                .findById(randomId)
                .orElse(null);

    }

    @Override
    public List<UserSoldDto> findAllUserWithMoreThanOneSoldProduct() {
        return userRepository
                .findAllUsersWhereSoldProductsIsMoreThanOneOrderByLastNameAndFirstName()
                .stream()
                .map(user -> modelMapper.map(user, UserSoldDto.class))
                .collect(Collectors.toList());
    }
}
