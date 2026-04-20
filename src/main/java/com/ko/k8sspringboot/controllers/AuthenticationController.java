package com.ko.k8sspringboot.controllers;

import com.ko.k8sspringboot.models.dto.LoginResponse;
import com.ko.k8sspringboot.models.dto.LoginUserDto;
import com.ko.k8sspringboot.models.dto.RegisterUserDto;
import com.ko.k8sspringboot.service.AuthenticationService;
import com.ko.k8sspringboot.service.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private JwtService jwtService;
    private AuthenticationService authService;
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService, JwtService jwtService, AuthenticationService authService) {
        this.authenticationService = authenticationService;
        this.jwtService = jwtService;
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterUserDto> register(@RequestBody RegisterUserDto registerUserDto) {
        RegisterUserDto register = authenticationService.register(registerUserDto);
        return ResponseEntity.ok(register);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginUserDto loginUserDto) {
        LoginResponse loginResponse = authenticationService.login(loginUserDto);

        return  ResponseEntity.ok(loginResponse);
    }
}
