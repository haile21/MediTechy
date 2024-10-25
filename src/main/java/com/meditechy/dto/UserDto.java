package com.meditechy.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto {

    private String firstname;
    private String lastname;

    @Pattern(regexp = "^251[97]\\d{8}$", flags = Pattern.Flag.UNICODE_CASE)
    @NotBlank(message = "phone is mandatory")
    private String phoneNumber;

    @Email(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;
    private String photo;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 6, max = 32)
    private String password;
}


