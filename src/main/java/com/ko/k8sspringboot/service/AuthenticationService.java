package com.ko.k8sspringboot.service;

import com.ko.k8sspringboot.models.dto.LoginResponse;
import com.ko.k8sspringboot.models.dto.LoginUserDto;
import com.ko.k8sspringboot.models.dto.RegisterUserDto;

public interface AuthenticationService {

    LoginResponse login(LoginUserDto registerUserDto);
    RegisterUserDto register(RegisterUserDto registerUserDto);
}
