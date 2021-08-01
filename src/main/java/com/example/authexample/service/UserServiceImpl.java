package com.example.authexample.service;

import com.example.authexample.domain.Role;
import com.example.authexample.domain.User;
import com.example.authexample.repository.RoleRepository;
import com.example.authexample.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Log4j2
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public User saveUser(User user) {
        log.info("Saving a user to the db");
        return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving a role {} to the db", role.getName());
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding a role {} to a user {}", roleName, username);
        User user = getUser(username);
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
        saveUser(user);
    }

    @Override
    public User getUser(String username) {
        log.info("Fetching a user {} ", username);
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getUsers() {
        log.info("Fetching all the users");
        return (List<User>) userRepository.findAll();
    }

    @Override
    public List<Role> getRoles() {
        log.info("Fetching all the roles");
        return (List<Role>) roleRepository.findAll();
    }
}
