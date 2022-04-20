//package com.example.demo.service.Impl;
//
//import com.example.demo.domain.User;
//import com.example.demo.dto.request.UserRegisterRequestDto;
//import com.example.demo.dto.response.UserRegisterResponseDto;
//import com.example.demo.repository.UserRepository;
//import com.example.demo.service.UserService;
//import com.example.demo.service.UserVerificationService;
//import com.example.demo.utils.JwtUtils;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.ArgumentCaptor;
//import org.modelmapper.ModelMapper;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import java.util.UUID;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//public class UserServiceImplTest {
//
//    UserRepository userRepository;
//    ModelMapper modelMapper;
//    PasswordEncoder passwordEncoder;
//    JwtUtils jwtUtils;
//    UserVerificationService userVerificationService;
//    User expectUser;
//    UserService userService;
//    UserRegisterRequestDto userRegisterRequestDto;
//    UserRegisterResponseDto userRegisterResponseDto;
//
//    @BeforeEach
//    // test register save
//    public void setUp() {
//        userRepository = mock(UserRepository.class);
//        modelMapper = mock(ModelMapper.class);
//        passwordEncoder = mock(PasswordEncoder.class);
//        jwtUtils = mock(JwtUtils.class);
//        userVerificationService = mock(UserVerificationService.class);
//        expectUser = mock(User.class);
//        userVerificationService = new UserVerificationServiceImpl(userRepository);
//        userService = new UserServiceImpl(userRepository, modelMapper, passwordEncoder, jwtUtils, userVerificationService);
//        userRegisterRequestDto = mock(UserRegisterRequestDto.class);
//
////        expectUser = mock(User.class);
////        registerRequestDto = mock(UserRegisterRequestDto.class);
////        userService = new UserServiceImpl(userRepository, null,null,null,null);
////        registerRequestDto = UserRegisterRequestDto.builder()
////                .username("cxtuan")
////                .email("cxtuan@gmail.com")
////                .password("123456")
////                .name("cxtuan").build();
////        expectUser = new User();
////        expectUser.setUsername("cxtuan");
////        expectUser.setEmail("cxtuan@gmail.com");
////        expectUser.setName("cxtuan");
////        expectUser.setIsActive(true);
////        expectUser.setIsDeleted(false);
//        when(userRepository.findByEmailOrUsername("cxtuan@gmail.com","cxtuan")).thenReturn(null);
//        when(userRepository.save(any())).thenReturn(expectUser);
//
//    }
//    @Test
//    public void register_save_user() {
//        UserRegisterResponseDto registerResponseDto = userService.register(registerRequestDto);
//        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
//        verify(userRepository).save(captor.capture());
//        User actualUser = captor.getValue();
//        assertThat(actualUser.getUsername()).isEqualTo(expectUser.getUsername());
//        assertThat(actualUser.getEmail()).isEqualTo(expectUser.getEmail());
//        assertThat(actualUser.getName()).isEqualTo(expectUser.getName());
//        assertThat(actualUser.getIsActive()).isEqualTo(expectUser.getIsActive());
//        assertThat(actualUser.getIsDeleted()).isEqualTo(expectUser.getIsDeleted());
//        assertThat(registerResponseDto.getEmail()).isEqualTo(expectUser.getEmail());
//
//    }
//}
