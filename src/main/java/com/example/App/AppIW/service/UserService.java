package com.example.App.AppIW.service;

import com.example.App.AppIW.model.UserEntity;
import com.example.App.AppIW.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public List<UserEntity> getAllUsers() {
        return repo.findAll();
    }

    public UserEntity createUser(UserEntity user) {
        user.setRegistryDate(LocalDateTime.now());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repo.save(user);
    }

    public UserEntity updateUser(int id, UserEntity newUser) {
        return repo.findById(id)
                .map(user -> {
                    user.setUsername(newUser.getUsername());

                    if (newUser.getPassword() != null && !newUser.getPassword().isEmpty()) {
                        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
                    }

                    return repo.save(user);
                })
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public void deleteUser(int id) {
        repo.deleteById(id);
    }

    public Optional<UserEntity> login(String username, String password) {

        Optional<UserEntity> user = repo.findByUsername(username);

        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
            return user;
        }

        return Optional.empty();
    }
}