package com.example.demo.service.user;

import com.example.demo.dto.response.PageResponseDto;
import com.example.demo.dto.response.user.UserResponseListDto;

import java.util.UUID;

public interface UserAdminService {

    Boolean blockUser(UUID userId);

    Boolean deleteUser(UUID userId);

    Boolean activeUser(UUID userId);

    PageResponseDto<UserResponseListDto> getUsers(int page, int size, String sortType, String sortBy, String search);
}
