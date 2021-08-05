package com.example.authexample.controller;

import com.example.authexample.auth.SignupRequest;
import com.example.authexample.domain.Role;
import com.example.authexample.domain.SystemRole;
import com.example.authexample.domain.User;
import com.example.authexample.repository.RoleRepository;
import com.example.authexample.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody SignupRequest signupRequest) {

        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity.badRequest().body("Duplicated username");
        }

        User user = new User();
        user.setUsername(signupRequest.getUsername());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));

        List<Role> userRoles = new ArrayList<>();
        for (String requestRole : signupRequest.getRoles()) {
            SystemRole systemRole = SystemRole.valueOf(requestRole);
            userRoles.add(roleRepository.findBySystemRole(systemRole).orElseThrow(RuntimeException::new));
        };

        user.setRoles(userRoles);
        userRepository.save(user);
        return ResponseEntity.ok().body("Successful registration");
    }
}
