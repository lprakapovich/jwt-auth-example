package com.example.authexample.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SignupRequest {

    private String username;
    private String password;
    private List<String> roles;
}
