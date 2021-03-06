package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.dto.response.user.UserResponseListDto;

import java.util.UUID;

public interface UserCRUDService extends CRUDService<User, UUID> {
    UserResponseListDto getUser(UUID userId);

    String getUserName(UUID userId);

    User findByUsername(String username);

    User findByEmailOrUsername(String username);
}
