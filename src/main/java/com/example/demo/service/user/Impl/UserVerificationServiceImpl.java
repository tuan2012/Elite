package com.example.demo.service.user.Impl;

import com.example.demo.repository.UserRepository;
import com.example.demo.service.user.UserVerificationService;
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

    @Override
    public Boolean checkExistUserWithUsernameAndEmail(String email, String username) {
        return userRepository.findByEmailOrUsername(email, username).orElse(null) != null;
    }
}
