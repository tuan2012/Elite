package com.example.demo.service.user.Impl;

import com.example.demo.domain.User;
import com.example.demo.dto.response.user.UserResponseListDto;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.user.UserCRUDService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserCRUDServiceImpl implements UserCRUDService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public String getUserName(UUID userId) {
        String nameById = userRepository.findNameById(userId);
        return nameById;
    }

    @Override
    public UserResponseListDto getUser(UUID userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
        return modelMapper.map(user, UserResponseListDto.class);
    }

    @Override
    public User findById(UUID userId) {
        return userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
    }

    @Override
    public Page<User> findAll(Specification specification, Pageable pageable) {
        return userRepository.findAll(specification, pageable);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("User not found"));
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findByEmailOrUsername(String username) {
        return userRepository.findByEmailOrUsername(username).orElseThrow(() -> new NotFoundException("User not found"));
    }

}
