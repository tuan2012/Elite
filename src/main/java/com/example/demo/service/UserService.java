package com.example.demo.service;

import com.example.demo.dto.request.UserLoginRequestDto;
import com.example.demo.dto.request.UserRegisterRequestDto;
import com.example.demo.dto.response.PageUserResponseDto;
import com.example.demo.dto.response.UserLoginResponseDto;
import com.example.demo.dto.response.UserRegisterResponseDto;
import com.example.demo.dto.response.UserResponeDto;

import java.util.UUID;

public interface UserService {
    String getUserName(UUID userId);

    UserResponeDto getUser(UUID userId);

    PageUserResponseDto<UserResponeDto> getUsers(int page, int size, String sortType, String sortBy);

    UserRegisterResponseDto register(UserRegisterRequestDto userRegisterRequestDto);

    UserLoginResponseDto login(UserLoginRequestDto userLoginRequestDto);
}
