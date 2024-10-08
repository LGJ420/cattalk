package com.example.cattalk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cattalk.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
