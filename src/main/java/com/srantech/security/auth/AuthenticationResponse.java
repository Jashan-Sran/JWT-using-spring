package com.srantech.security.auth;

import lombok.*;

@Data
@RequiredArgsConstructor
@Builder
public class AuthenticationResponse { // this class has only one filed token, this class will send back token to user

    private final String token;

}
