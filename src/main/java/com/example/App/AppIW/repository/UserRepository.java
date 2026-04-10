package com.example.App.AppIW.repository;

import com.example.App.AppIW.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository <UserEntity,Integer> {

    Optional<UserEntity> findByUsername(String username);
}
