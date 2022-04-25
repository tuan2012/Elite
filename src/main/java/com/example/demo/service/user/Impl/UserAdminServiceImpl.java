package com.example.demo.service.user.Impl;

import com.example.demo.domain.User;
import com.example.demo.dto.filter.UserFilterDto;
import com.example.demo.dto.response.PageUserResponseDto;
import com.example.demo.dto.response.UserResponseListDto;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.service.user.UserAdminService;
import com.example.demo.service.user.UserCRUDService;
import com.example.demo.specificaitons.UserSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserAdminServiceImpl implements UserAdminService {
    private final UserCRUDService userCRUDService;
    private final ModelMapper modelMapper;

    @Override
    public Boolean activeUser(UUID userId) {
        return changeActiveUser(true, userId);
    }

    @Override
    public Boolean blockUser(UUID userId) {
        return changeActiveUser(false, userId);
    }

    Boolean changeActiveUser(Boolean isActive, UUID userId) {
        User user = userCRUDService.findById(userId);
        user.setIsActive(isActive);
        try {
            userCRUDService.save(user);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new BadRequestException("Cannot active user");

        }
        return true;
    }

    @Override
    public Boolean deleteUser(UUID userId) {
        User user = userCRUDService.findById(userId);
        user.setIsDeleted(true);
        try {
            userCRUDService.save(user);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new BadRequestException("Cannot delete user");

        }
        return true;
    }


    @Override
    public PageUserResponseDto getUsers(int page, int size, String sortType, String sortBy, UserFilterDto userFilterDto) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(sortType.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy));

        Page<User> userPage = userCRUDService.findAll(new UserSpecification(userFilterDto), pageable);
        PageUserResponseDto pageUserResponseDto = new PageUserResponseDto<>();
        pageUserResponseDto.setPage(page);
        pageUserResponseDto.setSize(size);
        pageUserResponseDto.setTotalPages(userPage.getTotalPages());
        pageUserResponseDto.setTotalElements(userPage.getTotalElements());
        pageUserResponseDto.setElements(userPage.stream().map(user -> modelMapper.map(user, UserResponseListDto.class)).collect(Collectors.toList()));
        return pageUserResponseDto;
    }
}
