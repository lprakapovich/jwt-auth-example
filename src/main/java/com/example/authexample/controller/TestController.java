package com.example.authexample.controller;

import com.example.authexample.domain.User;
import com.example.authexample.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/test")
@RestController
@Log4j2
@RequiredArgsConstructor
public class TestController {

    private final UserRepository userRepository;

    @GetMapping
    public void allAccess() {
        log.info("Public resource accessed");
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPER_ADMIN')")
    public String adminAccess() {
        return "Admin resource accessed";
    }

    @GetMapping("/viewer")
    @PreAuthorize("hasRole('ROLE_VIEWER')")
    public String viewerAccess() {
        return "Viewer resource accessed";
    }

    @GetMapping("/billing")
    @PreAuthorize("hasRole('ROLE_BILLING')")
    public String billingAccess() {
        return "Finance & Billing resource accessed";
    }
}
