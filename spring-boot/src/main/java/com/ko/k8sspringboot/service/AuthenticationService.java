package com.ko.k8sspringboot.service;

import com.ko.k8sspringboot.models.dto.LoginUserDto;
import com.ko.k8sspringboot.models.dto.RegisterUserDto;
import com.ko.k8sspringboot.models.entity.UserEntity;

public interface AuthenticationService {

    UserEntity login(LoginUserDto registerUserDto) throws Exception;
    RegisterUserDto register(RegisterUserDto registerUserDto);
}
