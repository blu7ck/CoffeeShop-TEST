package com.blu4ck.Palm_Coffee.zero.controller;

import com.blu4ck.Palm_Coffee.zero.security.JwtTokenUtil;
import com.blu4ck.Palm_Coffee.zero.model.AuthenticationRequest;
import com.blu4ck.Palm_Coffee.zero.model.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/authenticate")
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws AuthenticationException {
        // Kimlik doğrulama
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
        );

        // JWT token oluşturma
        final String jwt = jwtTokenUtil.generateToken(authenticationRequest.getUsername());

        return new AuthenticationResponse(jwt);
    }
}
