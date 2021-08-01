package com.example.authexample.service;

import com.example.authexample.domain.Role;
import com.example.authexample.domain.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);

    Role saveRole(Role role);

    void addRoleToUser(String username, String roleName);

    User getUser(String username);

    List<User> getUsers();
}
