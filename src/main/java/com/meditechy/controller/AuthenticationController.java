package com.meditechy.controller;


import com.meditechy.dto.authentication.AuthenticationRequestDto;
import com.meditechy.dto.authentication.AuthenticationResponseDto;
import com.meditechy.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authentication;

    @PostMapping(value = "/login")
    public ResponseEntity<AuthenticationResponseDto> login(@RequestBody @Valid AuthenticationRequestDto authenticationRequest) {
        try {
            return authentication.login(authenticationRequest);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
}
