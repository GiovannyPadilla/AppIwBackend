package com.example.App.AppIW.controller;

import com.example.App.AppIW.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> data,
                                   HttpSession session) {

        String username = data.get("username");
        String password = data.get("password");

        return userService.login(username, password)
                .map(user -> {
                    session.setAttribute("user", user);
                    return ResponseEntity.ok(Map.of(
                            "success", true,
                            "user", user
                    ));
                })
                .orElseGet(() -> ResponseEntity.status(401).body(Map.of(
                        "success", false,
                        "message", "Credenciales incorrectas"
                )));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok(Map.of("success", true));
    }

    @GetMapping("/me")
    public ResponseEntity<?> me(HttpSession session) {
        Object user = session.getAttribute("user");

        if (user == null) {
            return ResponseEntity.status(401).body(Map.of("success", false));
        }

        return ResponseEntity.ok(Map.of(
                "success", true,
                "user", user
        ));
    }
}