package com.example.demo.service.Impl;

import com.example.demo.constants.Roles;
import com.example.demo.domain.Role;
import com.example.demo.domain.User;
import com.example.demo.dto.request.UserFilterDto;
import com.example.demo.dto.request.UserLoginRequestDto;
import com.example.demo.dto.request.UserRegisterRequestDto;
import com.example.demo.dto.response.PageUserResponseDto;
import com.example.demo.dto.response.UserLoginResponseDto;
import com.example.demo.dto.response.UserRegisterResponseDto;
import com.example.demo.dto.response.UserResponeDto;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.exceptions.UserBlockedException;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.service.UserVerificationService;
import com.example.demo.specificaitons.UserSpecification;
import com.example.demo.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final UserVerificationService userVerificationService;

    @Override
    public String getUserName(UUID userId) {
        String nameById = userRepository.findNameById(userId);
        return nameById;
    }

    @Override
    public UserResponeDto getUser(UUID userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
        return modelMapper.map(user, UserResponeDto.class);
    }

    private User findById(UUID userId) {
        return userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
    }

    private User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("User not found"));
    }

    @Override
    public PageUserResponseDto<UserResponeDto> getUsers(int page, int size, String sortType, String sortBy, UserFilterDto userFilterDto) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(sortType.equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy));

        Page<User> userPage = userRepository.findAll(new UserSpecification(userFilterDto), pageable);
        PageUserResponseDto<UserResponeDto> pageUserResponseDto = new PageUserResponseDto<>();
        pageUserResponseDto.setPage(page);
        pageUserResponseDto.setSize(size);
        pageUserResponseDto.setTotalPages(userPage.getTotalPages());
        pageUserResponseDto.setTotalElements(userPage.getTotalElements());
        pageUserResponseDto.setElements(modelMapper.map(userPage.getContent(), List.class));
        return pageUserResponseDto;
    }

    @Override
    public UserRegisterResponseDto registerUser(UserRegisterRequestDto userRegisterRequestDto) {

        Optional<Role> roleOpt = roleRepository.findByName(Roles.user);
        if (roleOpt.isPresent()) {
            return register(userRegisterRequestDto, roleOpt.get());

        } else {
            Role role = new Role();
            role.setName(Roles.user);
            return register(userRegisterRequestDto, role);
        }
    }

    @Override
    public UserRegisterResponseDto registerAdmin(UserRegisterRequestDto userRegisterRequestDto) {
        Optional<Role> roleOpt = roleRepository.findByName(Roles.admin);
        if (roleOpt.isPresent()) {
            return register(userRegisterRequestDto, roleOpt.get());

        } else {
            Role role = new Role();
            role.setName(Roles.admin);
            return register(userRegisterRequestDto, role);
        }
    }

    @Override
    public UserRegisterResponseDto register(UserRegisterRequestDto userRegisterRequestDto, Role role) {
        if (userVerificationService.checkExistUserWithUsernameAndEmail(userRegisterRequestDto.getEmail(), userRegisterRequestDto.getUsername())) {
            throw new BadRequestException("Username or Email is exist");
        }
        User user = modelMapper.map(userRegisterRequestDto, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(role);
        User userSaved = userRepository.save(user);
        UserRegisterResponseDto userRegisterResponseDto = modelMapper.map(userSaved, UserRegisterResponseDto.class);
        return userRegisterResponseDto;
    }

    @Override
    public UserLoginResponseDto login(UserLoginRequestDto userLoginRequestDto) {
        Optional<User> userOptional = userRepository.findByEmailOrUsername(userLoginRequestDto.getUsername());
        User user = userOptional.orElseThrow(() -> new NotFoundException("Username or Password does not correct"));
        if (!user.getIsActive()) {
            throw new UserBlockedException("User is not active");
        }
        if (user.getIsDeleted()) {
            throw new UserBlockedException("User is deleted");
        }
        if (passwordEncoder.matches(userLoginRequestDto.getPassword(), user.getPassword())) {
            UserLoginResponseDto loginResponseDto = modelMapper.map(user, UserLoginResponseDto.class);
            loginResponseDto.setAccessToken(jwtUtils.generateAccessToken(user.getUsername()));
            loginResponseDto.setRefreshToken(jwtUtils.generateRefreshToken(user.getUsername()));
            return loginResponseDto;
        } else {
            throw new NotFoundException("Username or Password does not correct");
        }
    }

    @Override
    public Boolean activeUser(UUID userId) {
        return changeActiveUser(true, userId);
    }

    @Override
    public Boolean deactiveUser(UUID userId) {
        return changeActiveUser(false, userId);
    }

    private Boolean changeActiveUser(Boolean isActive, UUID userId) {
        User user = findById(userId);
        user.setIsActive(isActive);
        try {
            userRepository.save(user);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new BadRequestException("Cannot active user");

        }
        return true;
    }

    @Override
    public Boolean deleteUser(UUID userId) {
        User user = findById(userId);
        user.setIsDeleted(true);
        try {
            userRepository.save(user);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new BadRequestException("Cannot delete user");

        }
        return true;
    }

    @Override
    public UserLoginResponseDto refresh(String refreshToken) {
        String username = jwtUtils.getUsernameFromToken(refreshToken);
        User user = findByUsername(username);
        UserLoginResponseDto loginResponseDto = modelMapper.map(user, UserLoginResponseDto.class);
        loginResponseDto.setAccessToken(jwtUtils.generateAccessToken(user.getUsername()));
        loginResponseDto.setRefreshToken(refreshToken);
        return loginResponseDto;
    }

}
