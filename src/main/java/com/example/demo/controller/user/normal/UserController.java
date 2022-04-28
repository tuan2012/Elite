package com.example.demo.controller.user.normal;

import com.example.demo.dto.request.user.UserRefreshTokenDto;
import com.example.demo.dto.response.ResponseBodyDto;
import com.example.demo.dto.response.user.UserLoginResponseDto;
import com.example.demo.service.UserService;
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
    public ResponseEntity<ResponseBodyDto<UserLoginResponseDto>> refresh(@Validated @RequestBody UserRefreshTokenDto refreshTokenDto) {
        ResponseBodyDto<UserLoginResponseDto> responseBodyDto = new ResponseBodyDto<>();
        responseBodyDto.setData(userService.refresh(refreshTokenDto.getRefreshToken()));
        responseBodyDto.setStatusCode(200);
        return ResponseEntity.ok(responseBodyDto);
    }
}
