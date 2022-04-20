package com.example.demo.service;

import com.example.demo.domain.Role;
import com.example.demo.dto.request.UserFilterDto;
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

    PageUserResponseDto<UserResponeDto> getUsers(int page, int size, String sortType, String sortBy, UserFilterDto userFilterDto);

    UserRegisterResponseDto registerUser(UserRegisterRequestDto userRegisterRequestDto);

    UserRegisterResponseDto registerAdmin(UserRegisterRequestDto userRegisterRequestDto);

    UserRegisterResponseDto register(UserRegisterRequestDto userRegisterRequestDto, Role role);

    UserLoginResponseDto login(UserLoginRequestDto userLoginRequestDto);


    Boolean activeUser(UUID userId);

    Boolean deactiveUser(UUID userId);

    Boolean deleteUser(UUID userId);

    UserLoginResponseDto refresh(String refreshToken);

}
