package com.example.medhub.controller;

import com.example.medhub.dto.AppointmentsDto;
import com.example.medhub.dto.request.AppointmentsCreateRequestDto;
import com.example.medhub.service.AppointmentsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/appointments")
public class AppointmentsController {

    private final AppointmentsService appointmentsService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(description = "Creates a new appointment.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "New appointment created successfully.")
    })
    public AppointmentsDto createAppointment(@RequestBody AppointmentsCreateRequestDto newAppointment) {
        return appointmentsService.createAppointment(newAppointment);
    }
    @GetMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Get Appointments for the current User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Appointments retrieved successfully")
    })
    public List<AppointmentsDto> getAppointmentsForCurrentUser() {
        return appointmentsService.getAppointmentsForCurrentUser();
    }

    @GetMapping("/user/{userId}/test")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get Appointments for a specific User by ID", description = "Retrieve all appointments for a given user ID. This is for testing purposes.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Appointments retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<List<AppointmentsDto>> getAppointmentsByUserIdForTest(@PathVariable Long userId) {
        try {
            List<AppointmentsDto> appointments = appointmentsService.getAppointmentsByUserId(userId);
            return new ResponseEntity<>(appointments, HttpStatus.OK);
        } catch (UsernameNotFoundException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}

