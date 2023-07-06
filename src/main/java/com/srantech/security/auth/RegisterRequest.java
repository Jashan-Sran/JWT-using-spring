package com.srantech.security.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RegisterRequest { // this class is for registering user for the first time

    private String firstName;

    private String lastName;

    private String email;

    private String password;

}
