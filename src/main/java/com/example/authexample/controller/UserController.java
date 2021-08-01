//package com.example.authexample.controller;
//
//import com.example.authexample.domain.Role;
//import com.example.authexample.domain.User;
//import com.example.authexample.service.UserService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
//
//import java.net.URI;
//import java.util.List;
//
//@RestController
//@RequestMapping("/api")
//@RequiredArgsConstructor
//public class UserController {
//
//    private final UserService userService;
//
//    @GetMapping("/users")
//    public ResponseEntity<List<User>> getUsers() {
//        return ResponseEntity.ok(userService.getUsers());
//    }
//
//    @GetMapping(path = "/users/{username}")
//    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
//        return ResponseEntity.ok(userService.getUser(username));
//    }
//
//    @GetMapping("/roles")
//    public ResponseEntity<List<Role>> getRoles() {
//        return ResponseEntity.ok(userService.getRoles());
//    }
//
//    @PostMapping("/users")
//    public ResponseEntity<User> saveUser(@RequestBody User user) {
//        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users").toUriString());
//        return ResponseEntity.created(uri).body(userService.saveUser(user));
//    }
//
//    @PostMapping("/roles")
//    public ResponseEntity<Role> saveRole(@RequestBody Role role) {
//        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/roles").toUriString());
//        return ResponseEntity.created(uri).body(userService.saveRole(role));
//    }
//}
