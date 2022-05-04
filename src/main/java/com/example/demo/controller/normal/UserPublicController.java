package com.example.demo.controller.normal;

import com.example.demo.dto.request.user.UserLoginRequestDto;
import com.example.demo.dto.request.user.UserRefreshTokenDto;
import com.example.demo.dto.request.user.UserRegisterRequestDto;
import com.example.demo.dto.response.ResponseBodyDto;
import com.example.demo.dto.response.user.UserLoginResponseDto;
import com.example.demo.dto.response.user.UserRegisterResponseDto;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/public/auth")
public class UserPublicController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ResponseBodyDto<UserRegisterResponseDto>> register(@Validated @RequestBody UserRegisterRequestDto requestDto) {
        userService.registerAdmin(requestDto);
        ResponseBodyDto<UserRegisterResponseDto> responseBodyDto = new ResponseBodyDto<>();
        responseBodyDto.setData(userService.registerUser(requestDto));
        responseBodyDto.setStatusCode(201);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBodyDto);

    }

    @PostMapping("/login")
    public ResponseEntity<ResponseBodyDto<UserLoginResponseDto>> login(@Validated @RequestBody UserLoginRequestDto requestDto) {
        ResponseBodyDto<UserLoginResponseDto> responseBodyDto = new ResponseBodyDto<>();
        responseBodyDto.setData(userService.login(requestDto));
        responseBodyDto.setStatusCode(201);
        return ResponseEntity.ok(responseBodyDto);
    }

    @PutMapping("/refresh-token")
    public ResponseEntity<ResponseBodyDto<UserLoginResponseDto>> refresh(@Validated @RequestBody UserRefreshTokenDto refreshTokenDto) {
        ResponseBodyDto<UserLoginResponseDto> responseBodyDto = new ResponseBodyDto<>();
        responseBodyDto.setData(userService.refresh(refreshTokenDto.getRefreshToken()));
        responseBodyDto.setStatusCode(200);
        return ResponseEntity.ok(responseBodyDto);
    }


}
