package com.example.medhub.controller;

import com.example.medhub.dto.AvailabilityDto;
import com.example.medhub.service.AvailabilityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/doctors/{doctorId}/availability")
@RequiredArgsConstructor
public class AvailabilityController {

    private final AvailabilityService availabilityService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get availability for a specific doctor", description = "Fetches available time slots for a doctor based on their ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Availability retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Doctor not found")
    })
    public List<AvailabilityDto> getDoctorAvailability(@PathVariable Long doctorId) {
        return availabilityService.getAvailabilityForDoctor(doctorId);
    }
}
