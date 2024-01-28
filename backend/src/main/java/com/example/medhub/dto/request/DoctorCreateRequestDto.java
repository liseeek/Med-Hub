package com.example.medhub.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = """
        Schema responsible for handling new doctorEntity creation operation.
        Contains all required information which must be provided to create and save a new doctorEntity in the system.
        """)
public class DoctorCreateRequestDto {

    @NotBlank
    @Size(min = 3, max = 48)
    @Schema(example = "John")
    private final String name;

    @NotBlank
    @Size(min = 3, max = 48)
    @Schema(example = "Doe")
    private final String surname;

    // Assuming location details are part of the doctorEntity creation
    @NotBlank
    @Size(max = 255)
    @Schema(example = "Central Hospital")
    private final String locationName;

    @NotBlank
    @Size(max = 255)
    @Schema(example = "1234 Main St")
    private final String address;

    @NotBlank
    @Size(max = 50)
    @Schema(example = "Springfield")
    private final String city;

    @NotBlank
    @Size(max = 50)
    @Schema(example = "USA")
    private final String country;

    // If you have specializations as part of doctorEntity creation
    // Use an appropriate type to represent the specializations, e.g., a list of IDs
    @Schema(example = "1")
    private final Long specializationId;


    // Constructors, getters and additional methods
}
