package com.example.demo.controller.normal;

import com.example.demo.domain.Orders;
import com.example.demo.domain.User;
import com.example.demo.dto.response.PageResponseDto;
import com.example.demo.dto.response.ResponseBodyDto;
import com.example.demo.security.UserDetailImpl;
import com.example.demo.service.OrdersService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/normal/orders")
public class OrderController {
    private final OrdersService orderService;

    @GetMapping("/all")
    public ResponseEntity<ResponseBodyDto<PageResponseDto<Orders>>> getOrder(@RequestParam(name = "search", required = false) String search,
                                                                             @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                                                                             @RequestParam(value = "size", required = false, defaultValue = "4") int size,
                                                                             @RequestParam(value = "sortBy", required = false, defaultValue = "createdDate") String sortBy,
                                                                             @RequestParam(value = "sortType", required = false, defaultValue = "desc") String sortType,
                                                                             @Parameter(hidden = true) @AuthenticationPrincipal UserDetailImpl userDetails) {
        User userInfo = userDetails.getUser();
        PageResponseDto<Orders> all = orderService.findAll(userInfo.getUserUuid(), page, size, sortType, sortBy, search);
        ResponseBodyDto responseBodyDto = new ResponseBodyDto();
        responseBodyDto.setData(all);
        responseBodyDto.setStatusCode(200);
        return ResponseEntity.ok(responseBodyDto);
    }

}
