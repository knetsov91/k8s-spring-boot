package com.ko.k8sspringboot.controllers;

import com.ko.k8sspringboot.models.dto.LoginResponse;
import com.ko.k8sspringboot.models.dto.LoginUserDto;
import com.ko.k8sspringboot.models.dto.RegisterUserDto;
import com.ko.k8sspringboot.models.entity.UserEntity;
import com.ko.k8sspringboot.security.AuthenticationDetails;
import com.ko.k8sspringboot.service.AuthenticationService;
import com.ko.k8sspringboot.service.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private JwtService jwtService;
    private AuthenticationService authService;
    private final AuthenticationService authenticationService;
    private final ModelMapper modelMapper;
    public AuthenticationController(AuthenticationService authenticationService, JwtService jwtService, AuthenticationService authService, ModelMapper modelMapper) {
        this.authenticationService = authenticationService;
        this.jwtService = jwtService;
        this.authService = authService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterUserDto> register(@RequestBody RegisterUserDto registerUserDto) {
        RegisterUserDto register = authenticationService.register(registerUserDto);
        return ResponseEntity.ok(register);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginUserDto loginUserDto) throws Exception {
        UserEntity loginUser = authenticationService.login(loginUserDto);

        AuthenticationDetails authenticationDetails = AuthenticationDetails.builder()
                .email(loginUserDto.getEmail())
                .role(loginUser.getUserRole())
                .build();
        String jwtToken = jwtService.generateToken(authenticationDetails);
        LoginResponse loginResponse = new LoginResponse().setToken(jwtToken).setExpiresIn(jwtService.getJwtExpiration());
        return  ResponseEntity.ok(loginResponse);
    }


}
