package com.example.demo.controller.user.normal;

import com.example.demo.dto.request.user.UserLoginRequestDto;
import com.example.demo.dto.request.user.UserRegisterRequestDto;
import com.example.demo.dto.response.user.UserLoginResponseDto;
import com.example.demo.dto.response.user.UserRegisterResponseDto;
import com.example.demo.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/public/users")
public class UserPublicController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponseDto> register(@Validated @RequestBody UserRegisterRequestDto requestDto) {
        userService.registerAdmin(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.registerUser(requestDto));

    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDto> login(@Validated @RequestBody UserLoginRequestDto requestDto) {
        return ResponseEntity.ok(userService.login(requestDto));
    }


}
