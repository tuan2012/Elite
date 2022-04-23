package com.example.demo.service;

import com.example.demo.dto.request.UserFilterDto;
import com.example.demo.dto.response.PageUserResponseDto;
import com.example.demo.dto.response.UserResponeDto;

import java.util.UUID;

public interface UserAdminService {

    Boolean blockUser(UUID userId);

    Boolean deleteUser(UUID userId);

    Boolean activeUser(UUID userId);

    PageUserResponseDto<UserResponeDto> getUsers(int page, int size, String sortType, String sortBy, UserFilterDto userFilterDto);
}
