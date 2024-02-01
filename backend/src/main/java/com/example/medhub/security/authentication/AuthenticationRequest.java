package com.example.medhub.security.authentication;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
class AuthenticationRequest {

    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
