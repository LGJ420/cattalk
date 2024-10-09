package com.example.cattalk.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cattalk.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

    Optional<User> findByUserId(String userId);
}
