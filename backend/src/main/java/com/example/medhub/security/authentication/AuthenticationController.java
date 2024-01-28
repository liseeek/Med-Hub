package com.example.medhub.security.authentication;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequiredArgsConstructor
class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("v1/auth/signup")
    AuthenticationResponse authenticateUser(@RequestBody AuthenticationRequest authenticationRequest) {
        try {
            return authenticationService.signIn(authenticationRequest);
        } catch (Exception e) {
            throw e;
        }
    }
}