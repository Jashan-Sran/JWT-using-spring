package com.srantech.security.auth;

import lombok.*;

@Data
@RequiredArgsConstructor
@Builder
public class AuthenticationResponse {

    private final String token;

}
