package com.example.demo.service.Impl;

import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserVerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserVerificationServiceImpl implements UserVerificationService {

    private final UserRepository userRepository;

    @Override
    public Boolean checkExistUserWithUsername(String username) {
        return userRepository.findByUsername(username).orElse(null) != null;
    }

    @Override
    public Boolean checkExistUserWithEmail(String email) {
        return userRepository.findByEmail(email).orElse(null) != null;
    }
}
