package com.example.demo.controller.admin;

import com.example.demo.dto.request.UserFilterDto;
import com.example.demo.dto.response.PageUserResponseDto;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/users")

public class UserAdminController {
    private final UserService userService;

    //    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<PageUserResponseDto> getUsers(UserFilterDto userFilterDto,
                                                        @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                                        @RequestParam(value = "size", required = false, defaultValue = "4") int size,
                                                        @RequestParam(value = "sortBy", required = false, defaultValue = "createdDate") String sortBy,
                                                        @RequestParam(value = "sortType", required = false, defaultValue = "desc") String sortType) {
        System.out.println(userFilterDto.getName());
        return ResponseEntity.ok(userService.getUsers(page, size, sortType, sortBy, userFilterDto));
    }

    @PutMapping("/de-activation")
    public ResponseEntity<Boolean> activation(@RequestParam("userUuid") UUID id) {
        return ResponseEntity.ok(userService.deactiveUser(id));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteUser(@RequestParam("userUuid") UUID id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }
}
