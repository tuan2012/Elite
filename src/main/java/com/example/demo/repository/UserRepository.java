package com.example.demo.repository;

import com.example.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>, JpaSpecificationExecutor<User> {
    @Query("select u.username from User u where u.userUuid = ?1")
    String findNameById(UUID id);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    @Query("select u from User u where u.username = ?1 or u.email = ?1")
    Optional<User> findByEmailOrUsername(String param);

    @Query("select u from User u where u.username = ?2 or u.email = ?1")
    Optional<User> findByEmailOrUsername(String email, String username);

//    Page<User> findAll(int page, int size, String sortBy, String sortType);
}
