package com.example.demo.service;

import com.example.demo.domain.Role;
import com.example.demo.dto.request.user.UserLoginRequestDto;
import com.example.demo.dto.request.user.UserRegisterRequestDto;
import com.example.demo.dto.response.user.UserLoginResponseDto;
import com.example.demo.dto.response.user.UserRegisterResponseDto;

public interface UserService {
    UserRegisterResponseDto registerUser(UserRegisterRequestDto userRegisterRequestDto);

    UserRegisterResponseDto registerAdmin(UserRegisterRequestDto userRegisterRequestDto);

    UserRegisterResponseDto register(UserRegisterRequestDto userRegisterRequestDto, Role role);

    UserLoginResponseDto login(UserLoginRequestDto userLoginRequestDto);


    UserLoginResponseDto refresh(String refreshToken);

}
