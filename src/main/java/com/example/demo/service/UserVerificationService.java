package com.example.demo.service;

public interface UserVerificationService {
    Boolean checkExistUserWithUsername(String username);

    Boolean checkExistUserWithEmail(String email);

    Boolean checkExistUserWithUsernameAndEmail(String username, String email);
}
