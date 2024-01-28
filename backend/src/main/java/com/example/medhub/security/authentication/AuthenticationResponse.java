package com.example.medhub.security.authentication;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder
@Getter
@RequiredArgsConstructor
class AuthenticationResponse {

    private final String jwtToken;
}
