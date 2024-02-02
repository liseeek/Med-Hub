package com.example.medhub.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Schema
public class AppointmentsCreateRequestDto {

    @NotNull
    @Schema(example = "1")
    private Long userId;

    @NotNull
    @Schema(example = "2")
    private Long doctorId;

    @NotNull
    @Schema(example = "2023-01-01")
    private LocalDate date;

    @NotNull
    @Schema(type = "string", format = "time", example = "14:00")
    private LocalTime time;

    @NotNull
    private Long locationId;

}
