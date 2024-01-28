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
    @GetMapping("/user/{userId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Get Appointments for User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Appointments retrieved successfully")
    })
    public List<AppointmentsDto> getAppointmentsByUserId(@PathVariable Long userId) {
        return appointmentsService.getAppointmentsByUserId(userId);
    }
    // Additional endpoints for other operations like get, update, delete appointments
    // Example:
    // @GetMapping("/{id}")
    // public AppointmentDto getAppointment(@PathVariable Long id) {
    //     return appointmentService.getAppointment(id);
    // }
}
