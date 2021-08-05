package com.example.authexample.application;

import com.example.authexample.domain.Role;
import com.example.authexample.domain.SystemRole;
import com.example.authexample.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.function.Predicate;

@Component
@Log4j2
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    
    private final RoleRepository roleRepository; 

    // TODO switch to flyway
    @Override
    public void run(String... args) {
        log.info("[DataLoader] Loading roles to the database...");
        loadRoles();
    }

    private void loadRoles() {
        Arrays.stream(SystemRole.values())
                .filter(Predicate.not(roleRepository::existsBySystemRole))
                .forEach(systemRole -> roleRepository.save(new Role(systemRole)));
    }
}
