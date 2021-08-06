package com.example.authexample.controller;

import com.example.authexample.auth.SignupRequest;
import com.example.authexample.domain.Role;
import com.example.authexample.domain.SystemRole;
import com.example.authexample.domain.User;
import com.example.authexample.repository.RoleRepository;
import com.example.authexample.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody SignupRequest signupRequest) {

        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity.badRequest().body("duplicated username");
        }

        if (signupRequest.getRoles() == null || signupRequest.getRoles().isEmpty()) {
            return ResponseEntity.badRequest().body("empty role list");
        }

        List<Role> roles = signupRequest.getRoles().stream()
                .filter(role -> Arrays.stream(SystemRole.values())
                        .anyMatch(systemRole -> systemRole.name().equals(role)))
                .map(role -> roleRepository.findBySystemRole(SystemRole.valueOf(role)))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());

        userRepository.save(new User(signupRequest.getUsername(), signupRequest.getPassword(), roles));
        return ResponseEntity.ok().body("successful registration");
    }
}
