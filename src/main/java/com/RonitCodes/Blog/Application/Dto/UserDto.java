package com.RonitCodes.Blog.Application.Dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for User.
 * Contains validation constraints to ensure safe and valid user input.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    @NotNull(message = "Username must not be null")
    @Size(min = 3, max = 100, message = "Username must be between 3 and 100 characters")
    private String username;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAbout() {
        return about;
    }

    @NotNull(message = "Password must not be null")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character (@$!%*?&)"
    )
    private String password;

    @NotEmpty(message = "Email must not be empty")
    @Email(message = "Invalid email format")
    private String email;

    @NotNull(message = "Phone number must not be null")
    @Pattern(
            regexp = "^(98\\d{8}|97\\d{8}|0\\d{1,2}-\\d{6,7})$",
            message = "Invalid Nepali phone number. Use formats like 9800000000 or area-code like 01-1234567"
    )
    private String phoneNumber;

    @NotNull(message = "About section must not be null")
    @Size(max = 1000, message = "About section must not exceed 1000 characters")
    private String about;
}
