package com.example.medhub.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@AllArgsConstructor
@Schema(description = """
        Schema responsible for handling new appointment creation operation.
        Contains all required information which must be provided to create and save a new appointment in the system.
        """)
public class AppointmentsCreateRequestDto {

    @NotNull
    @Schema(example = "1")
    private final Long userId;

    @NotNull
    @Schema(example = "2")
    private final Long doctorId;

    @NotNull
    @Schema(example = "2023-01-01")
    private final LocalDate date;

    @NotNull
    @Schema(type = "string", format = "time", example = "14:00")
    private final LocalTime time;

    @NotNull
    @Schema(example = "2")
    private final Long locationId;


    // Constructors, getters, and other necessary methods
}
