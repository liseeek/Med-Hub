package com.example.medhub.dto.request;

import com.example.medhub.exception.MedHubServiceException;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
@Schema(description = """
        Schema responsible of handling new userEntity creation operation.
        Contains all required information which must be provided to create and save new userEntity in the system.
        """)
public class UserCreateRequestDto {

    @NotBlank
    @Size(min = 3, max = 48)
    @Schema(example = "Jan")
    private final String name;

    @NotBlank
    @Size(min = 3, max = 48)
    @Schema(example = "Kowalski")
    private final String surname;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
    @Size(max = 48)
    @Schema(example = "kowalski99@gmail.com")
    private final String email;

    @NotBlank
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$")
    @Size(max = 120)
    @Schema(example = "Password1$")
    private final String password;

    @NotBlank
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$")
    @Size(max = 120)
    @Schema(example = "Password1$")
    private String passwordConfirmation;

    @NotBlank
    @Size(max = 48)
    @Schema(example = "514098754")
    private final String phoneNumber;


    public UserCreateRequestDto(String name, String surname, String email, String password, String phoneNumber, String passwordConfirmation) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.passwordConfirmation = passwordConfirmation;
        this.phoneNumber = phoneNumber;
        validate(this);
    }

    private static void validate(UserCreateRequestDto dto) {
        if (!dto.getPassword().equals(dto.passwordConfirmation)) {
            throw new MedHubServiceException("Provided fields password and passwordConfirmation are not same.");
        }
    }
}
