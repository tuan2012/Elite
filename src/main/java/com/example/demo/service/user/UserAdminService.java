package com.example.demo.service.user;

import com.example.demo.domain.User;
import com.example.demo.dto.filter.UserFilterDto;
import com.example.demo.dto.response.PageUserResponseDto;

import java.util.UUID;

public interface UserAdminService {

    Boolean blockUser(UUID userId);

    Boolean deleteUser(UUID userId);

    Boolean activeUser(UUID userId);

    PageUserResponseDto<User> getUsers(int page, int size, String sortType, String sortBy, UserFilterDto userFilterDto);
}
