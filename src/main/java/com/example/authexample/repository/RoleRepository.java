package com.example.authexample.repository;

import com.example.authexample.domain.Role;
import com.example.authexample.domain.SystemRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findBySystemRole(SystemRole systemRole);

    boolean existsBySystemRole(SystemRole systemRole);
}
