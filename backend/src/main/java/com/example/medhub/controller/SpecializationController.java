package com.example.medhub.controller;

import com.example.medhub.dto.SpecializationDto;
import com.example.medhub.service.SpecializationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/specializations")
public class SpecializationController {

    private final SpecializationService specializationService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Get all specializations.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Specializations retrieved successfully.")
    })
    public List<SpecializationDto> getAllSpecializations() {
        return specializationService.getAllSpecializations();
    }
}
