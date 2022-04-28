package com.example.demo.service.Impl.user;

import com.example.demo.constants.Roles;
import com.example.demo.domain.Role;
import com.example.demo.domain.User;
import com.example.demo.dto.request.user.UserLoginRequestDto;
import com.example.demo.dto.request.user.UserRegisterRequestDto;
import com.example.demo.dto.response.user.UserLoginResponseDto;
import com.example.demo.dto.response.user.UserRegisterResponseDto;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.exceptions.UserBlockedException;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.UserCRUDService;
import com.example.demo.service.UserService;
import com.example.demo.service.UserVerificationService;
import com.example.demo.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserCRUDService userCRUDService;
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final UserVerificationService userVerificationService;

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
        User userSaved = userCRUDService.save(user);
        UserRegisterResponseDto userRegisterResponseDto = modelMapper.map(userSaved, UserRegisterResponseDto.class);
        return userRegisterResponseDto;
    }

    @Override
    public UserLoginResponseDto login(UserLoginRequestDto userLoginRequestDto) {
        User user = userCRUDService.findByEmailOrUsername(userLoginRequestDto.getUsername());
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
    public UserLoginResponseDto refresh(String refreshToken) {
        String username = jwtUtils.getUsernameFromToken(refreshToken);
        User user = userCRUDService.findByUsername(username);
        UserLoginResponseDto loginResponseDto = modelMapper.map(user, UserLoginResponseDto.class);
        loginResponseDto.setAccessToken(jwtUtils.generateAccessToken(user.getUsername()));
        loginResponseDto.setRefreshToken(refreshToken);
        return loginResponseDto;
    }

}
