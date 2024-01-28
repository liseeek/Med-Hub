package com.example.medhub.security.authentication;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
class AuthenticationRequest {

    @NotBlank
    private final String email;

    @NotBlank
    private final String password;
}
