package com.example.demo.service.Impl;

import com.example.demo.domain.User;
import com.example.demo.dto.request.UserLoginRequestDto;
import com.example.demo.dto.request.UserRegisterRequestDto;
import com.example.demo.dto.response.PageUserResponseDto;
import com.example.demo.dto.response.UserLoginResponseDto;
import com.example.demo.dto.response.UserRegisterResponseDto;
import com.example.demo.dto.response.UserResponeDto;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.service.UserVerificationService;
import com.example.demo.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
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
        Optional<User> user = userRepository.findById(userId);
        UserResponeDto userResponeDto = modelMapper.map(user.get(), UserResponeDto.class);
        return userResponeDto;
    }

    @Override
    public PageUserResponseDto<UserResponeDto> getUsers(int page, int size, String sortType, String sortBy) {
        return null;
    }

    @Override
    public UserRegisterResponseDto register(UserRegisterRequestDto userRegisterRequestDto) {
        if (userVerificationService.checkExistUserWithUsername(userRegisterRequestDto.getUsername())) {
            throw new BadRequestException("Username is exist");
        }
        if (userVerificationService.checkExistUserWithEmail(userRegisterRequestDto.getEmail())) {
            throw new BadRequestException("Email is exist");
        }
        User user = modelMapper.map(userRegisterRequestDto, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User userSaved = userRepository.save(user);
        UserRegisterResponseDto userRegisterResponseDto = modelMapper.map(userSaved, UserRegisterResponseDto.class);
        return userRegisterResponseDto;
    }

    @Override
    public UserLoginResponseDto login(UserLoginRequestDto userLoginRequestDto) {
        Optional<User> userOptional = userRepository.findByEmailOrUsername(userLoginRequestDto.getUsername());
        User user = userOptional.orElseThrow(() -> new NotFoundException("Username or Password does not correct"));
        if (passwordEncoder.matches(userLoginRequestDto.getPassword(), user.getPassword())) {
            UserLoginResponseDto loginResponseDto = modelMapper.map(user, UserLoginResponseDto.class);
            loginResponseDto.setAccessToken(jwtUtils.generateAccessToken(user.getUsername()));
            loginResponseDto.setRefreshToken(jwtUtils.generateRefreshToken(user.getUsername()));
            return loginResponseDto;
        } else {
            throw new NotFoundException("Username or Password does not correct");
        }
    }

//    @Override
//    public PageUserResponseDto<UserResponeDto> getUsers(int page, int size, String sortType, String sortBy) {
//        Page<User> users = userRepository.findAll(new Specification<User>() {
//            @Override
//            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//
//
//            }
//        });
//        PageUserResponseDto<UserResponeDto> pageUserResponseDto = new PageUserResponseDto<>();
//        pageUserResponseDto.setPage(users.getPageable().getPageNumber());
//        pageUserResponseDto.setSize(users.getPageable().getPageSize());
//        pageUserResponseDto.setTotalElements(users.getTotalElements());
//        pageUserResponseDto.setTotalPages(users.getTotalPages());
//        pageUserResponseDto.setElements(users.stream().map(user -> modelMapper.map(user, UserResponeDto.class)).collect(Collectors.toList()));
//        return pageUserResponseDto;
//    }

}
