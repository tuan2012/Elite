package com.example.demo.service;

import com.example.demo.dto.response.PageResponseDto;
import com.example.demo.dto.response.user.UserResponseListDto;
import com.example.demo.dto.response.user.UserUpdateResponseDto;

import java.util.UUID;

public interface UserAdminService {

    UserUpdateResponseDto blockUser(UUID userId);

    UserUpdateResponseDto deleteUser(UUID userId);

    UserUpdateResponseDto activeUser(UUID userId);

    PageResponseDto<UserResponseListDto> getUsers(int page, int size, String sortType, String sortBy, String search);
}
