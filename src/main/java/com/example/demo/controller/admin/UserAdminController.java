package com.example.demo.controller.admin;

import com.example.demo.dto.request.user.UserRegisterRequestDto;
import com.example.demo.dto.response.PageResponseDto;
import com.example.demo.dto.response.ResponseBodyDto;
import com.example.demo.dto.response.user.UserRegisterResponseDto;
import com.example.demo.dto.response.user.UserResponseListDto;
import com.example.demo.dto.response.user.UserUpdateResponseDto;
import com.example.demo.service.UserAdminService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/users")

public class UserAdminController {
    private final UserAdminService userAdminService;
    private final UserService userService;


    @GetMapping("/all")
    public ResponseEntity<ResponseBodyDto<PageResponseDto<UserResponseListDto>>> getUsers(@RequestParam(value = "search", required = false) String search,
                                                                                          @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                                                                          @RequestParam(value = "size", required = false, defaultValue = "4") int size,
                                                                                          @RequestParam(value = "sortBy", required = false, defaultValue = "createdDate") String sortBy,
                                                                                          @RequestParam(value = "sortType", required = false, defaultValue = "desc") String sortType) {
        ResponseBodyDto<PageResponseDto<UserResponseListDto>> responseBodyDto = new ResponseBodyDto<PageResponseDto<UserResponseListDto>>();
        responseBodyDto.setData(userAdminService.getUsers(page, size, sortType, sortBy, search));
        responseBodyDto.setStatusCode(200);
        return ResponseEntity.ok(responseBodyDto);
    }

    @PutMapping("/de-activation")
    public ResponseEntity<ResponseBodyDto<UserUpdateResponseDto>> activation(@RequestParam("userUuid") UUID id) {
        ResponseBodyDto<UserUpdateResponseDto> responseBodyDto = new ResponseBodyDto<>();
        responseBodyDto.setData(userAdminService.blockUser(id));
        responseBodyDto.setStatusCode(200);
        return ResponseEntity.ok(responseBodyDto);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseBodyDto<UserUpdateResponseDto>> deleteUser(@RequestParam("userUuid") UUID id) {
        ResponseBodyDto<UserUpdateResponseDto> responseBodyDto = new ResponseBodyDto<>();
        responseBodyDto.setData(userAdminService.deleteUser(id));
        responseBodyDto.setStatusCode(200);
        return ResponseEntity.ok(responseBodyDto);

    }

    @PostMapping("/")
    public ResponseEntity<ResponseBodyDto<UserRegisterResponseDto>> createUser(@RequestBody UserRegisterRequestDto userRegisterRequestDto) {
        ResponseBodyDto<UserRegisterResponseDto> responseBodyDto = new ResponseBodyDto<UserRegisterResponseDto>();
        responseBodyDto.setData(userService.registerAdmin(userRegisterRequestDto));
        responseBodyDto.setStatusCode(200);
        return ResponseEntity.ok(responseBodyDto);
    }
}
