package com.example.demo.service.user;

public interface UserVerificationService {
    Boolean checkExistUserWithUsername(String username);

    Boolean checkExistUserWithEmail(String email);

    Boolean checkExistUserWithUsernameAndEmail(String username, String email);
}
