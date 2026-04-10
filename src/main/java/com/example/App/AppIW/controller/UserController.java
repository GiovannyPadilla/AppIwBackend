package com.example.App.AppIW.controller;

import com.example.App.AppIW.model.UserEntity;
import com.example.App.AppIW.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public List<UserEntity> getAll() {
        return service.getAllUsers();
    }

    @PostMapping
    public UserEntity create(@RequestBody UserEntity user) {
        return service.createUser(user);
    }

    @PutMapping("/{id}")
    public UserEntity update(@PathVariable int id, @RequestBody UserEntity user) {
        return service.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        service.deleteUser(id);
    }
}