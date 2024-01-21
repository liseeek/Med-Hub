package com.example.medhub.controller;

import com.example.medhub.dto.create.UserCreateRequestDto;
import com.example.medhub.dto.UserDto;
import com.example.medhub.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping( path = "/registration", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(description = "Creates new service user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "New user created successfully.")
    })
    public UserDto createUser(@RequestBody UserCreateRequestDto newUser) {
        return userService.saveUser(newUser);
    }
}