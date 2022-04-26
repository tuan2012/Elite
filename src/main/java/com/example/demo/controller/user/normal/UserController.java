package com.example.demo.controller.user.normal;

import com.example.demo.dto.request.UserRefreshTokenDto;
import com.example.demo.dto.response.UserLoginResponseDto;
import com.example.demo.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PutMapping("/refresh-token")
    public ResponseEntity<UserLoginResponseDto> refresh(@Validated @RequestBody UserRefreshTokenDto refreshTokenDto) {
        return ResponseEntity.ok(userService.refresh(refreshTokenDto.getRefreshToken()));
    }
}
