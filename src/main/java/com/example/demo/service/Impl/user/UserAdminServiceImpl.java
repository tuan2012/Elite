package com.example.demo.service.Impl.user;

import com.example.demo.domain.User;
import com.example.demo.dto.filter.SearchCriteria;
import com.example.demo.dto.response.PageResponseDto;
import com.example.demo.dto.response.user.UserResponseListDto;
import com.example.demo.dto.response.user.UserUpdateResponseDto;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.service.UserAdminService;
import com.example.demo.service.UserCRUDService;
import com.example.demo.specifications.SpecificationBuilder;
import com.example.demo.utils.ParseSearchUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserAdminServiceImpl implements UserAdminService {
    private final UserCRUDService userCRUDService;
    private final ModelMapper modelMapper;
    private final SpecificationBuilder specificationBuilder;
    private final ParseSearchUtils parseSearchUtils;

    @Override
    public UserUpdateResponseDto activeUser(UUID userId) {
        return changeActiveUser(true, userId);
    }

    @Override
    public UserUpdateResponseDto blockUser(UUID userId) {
        return changeActiveUser(false, userId);
    }

    UserUpdateResponseDto changeActiveUser(Boolean isActive, UUID userId) {
        User user = userCRUDService.findById(userId);
        user.setIsActive(isActive);
        User userSaved = null;
        try {
            userSaved = userCRUDService.save(user);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new BadRequestException("Cannot active user");

        }
        return modelMapper.map(userSaved, UserUpdateResponseDto.class);

    }

    @Override
    public UserUpdateResponseDto deleteUser(UUID userId) {
        User user = userCRUDService.findById(userId);
        user.setIsDeleted(true);
        User userSaved = null;
        try {
            userSaved = userCRUDService.save(user);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new BadRequestException("Cannot delete user");

        }
        return modelMapper.map(userSaved, UserUpdateResponseDto.class);
    }


    @Override
    public PageResponseDto<UserResponseListDto> getUsers(int page, int size, String sortType, String sortBy, String search) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(sortType.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy));
        List<SearchCriteria> listSearchCriteria = parseSearchUtils.parseSearch(search);

        Page<User> userPage = userCRUDService.findAll(specificationBuilder.createSpecification(listSearchCriteria), pageable);
        PageResponseDto pageResponseDto = PageResponseDto.builder()
                .page(page)
                .size(size)
                .totalPages(userPage.getTotalPages())
                .totalElements(userPage.getTotalElements())
                .elements(userPage.stream().map(user -> modelMapper.map(user, UserResponseListDto.class)).collect(Collectors.toList())).build();
        return pageResponseDto;
    }
}
