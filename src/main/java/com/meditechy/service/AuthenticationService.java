package com.meditechy.service;

import com.meditechy.dto.authentication.AuthenticationRequestDto;
import com.meditechy.dto.authentication.AuthenticationResponseDto;
import com.meditechy.util.JwtTokenUtil;
import com.meditechy.util.UserPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final JwtUserDetailsService userDetailsService;

    @Transactional
    public ResponseEntity<AuthenticationResponseDto> login(AuthenticationRequestDto authenticationRequest) {

        authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());

        UserPrincipal userDetails = (UserPrincipal) userDetailsService.loadUserByUsername(authenticationRequest.getEmail());

        final String token = jwtTokenUtil.generateToken(userDetails);

        log.info("User Details {}", userDetails.getUsername());

        return ResponseEntity.ok(new AuthenticationResponseDto(token));
    }

    public void authenticate(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new RuntimeException("USER_DISABLED");
        } catch (Exception ex) {
            throw new RuntimeException("Authentication failed! " + ex.getMessage());
        }
    }
}