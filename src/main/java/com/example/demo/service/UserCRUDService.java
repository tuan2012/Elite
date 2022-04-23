package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.dto.response.UserResponeDto;

import java.util.UUID;

public interface UserCRUDService extends CRUDService<User, UUID> {
    UserResponeDto getUser(UUID userId);

    String getUserName(UUID userId);

    User findByUsername(String username);

    User findByEmailOrUsername(String username);
}
