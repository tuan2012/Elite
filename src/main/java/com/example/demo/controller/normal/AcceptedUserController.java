package com.example.demo.controller.normal;

import com.example.demo.dto.request.UserLoginRequestDto;
import com.example.demo.dto.request.UserRefreshTokenDto;
import com.example.demo.dto.request.UserRegisterRequestDto;
import com.example.demo.dto.response.UserLoginResponseDto;
import com.example.demo.dto.response.UserRegisterResponseDto;
import com.example.demo.service.UserService;
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
@RequestMapping("/apt/users")
public class AcceptedUserController {
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

    @PostMapping("/refresh")
    public ResponseEntity<UserLoginResponseDto> refresh(@Validated @RequestBody UserRefreshTokenDto refreshTokenDto) {
        return ResponseEntity.ok(userService.refresh(refreshTokenDto.getRefreshToken()));
    }


}
