package com.example.medhub.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Schema
public class DoctorCreateRequestDto {

    @NotBlank
    @Size(min = 3, max = 48)
    @Schema(example = "John")
    private String name;

    @NotBlank
    @Size(min = 3, max = 48)
    @Schema(example = "Doe")
    private String surname;


    @NotBlank
    @Size(max = 255)
    @Schema(example = "Central Hospital")
    private String locationName;

    @NotBlank
    @Size(max = 255)
    @Schema(example = "1234 Main St")
    private String address;

    @NotBlank
    @Size(max = 50)
    @Schema(example = "Springfield")
    private String city;

    @NotBlank
    @Size(max = 50)
    @Schema(example = "USA")
    private String country;

    @Schema(example = "1")
    private Long specializationId;

}
