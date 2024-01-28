package com.example.medhub.controller;

import com.example.medhub.dto.request.DoctorCreateRequestDto;
import com.example.medhub.dto.DoctorDto;
import com.example.medhub.service.DoctorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    @PostMapping( path = "/adding", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(description = "Creates a new doctor.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "New doctor created successfully.")
    })
    public DoctorDto createDoctor(@RequestBody DoctorCreateRequestDto newDoctor) {
        return doctorService.saveDoctor(newDoctor);
    }

    @GetMapping(path = "/getAllDoctors")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Get Doctors")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Doctors get succesfully")
    })
    public List<DoctorDto> getDoctors() {
        return doctorService.getAllDoctors();
    }

    @GetMapping(path = "/getDoctor/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Get Doctor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Doctors get succesfully")
    })
    public DoctorDto getDoctor(@PathVariable Long id) {
        return doctorService.getDoctor(id);
    }
}
