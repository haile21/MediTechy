package com.meditechy.model;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

//    //@NotBlank(message = "captcha response is mandatory")
//    private String captchaResponse;

    @NotBlank(message = "Email  is Required")
    private String email;
    @NotBlank(message = "Password is Required")
    private String password;
}
