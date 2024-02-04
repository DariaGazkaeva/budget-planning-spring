package ru.kpfu.itis.dariagazkaeva.budgetplanning.dto;

import javax.validation.constraints.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class LoginDto {

    @NotBlank(message = "Email must not be empty")
    @Size(min = 5, max = 50)
    @Pattern(regexp = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]+$", message = "Incorrect email")
    private String email;

    @NotBlank(message = "Password must not be empty")
    @Size(min = 8, max = 30, message = "Password length must be between 8 and 30")
    private String password;
}
