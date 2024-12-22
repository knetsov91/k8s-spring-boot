package com.ko.k8sspringboot.service.impl;

import com.ko.k8sspringboot.models.dto.LoginUserDto;
import com.ko.k8sspringboot.models.dto.RegisterUserDto;
import com.ko.k8sspringboot.models.entity.UserEntity;
import com.ko.k8sspringboot.repository.UserRepository;
import com.ko.k8sspringboot.service.AuthenticationService;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private ModelMapper modelMapper;

    public AuthenticationServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.modelMapper = modelMapper;
    }

    @Override
    public RegisterUserDto register(RegisterUserDto registerUserDto) {
        UserEntity user = new UserEntity();
        user.setFullName(registerUserDto.getFullName())
                .setEmail(registerUserDto.getEmail())
                .setPassword(passwordEncoder.encode(registerUserDto.getPassword()));
        RegisterUserDto map = modelMapper.map(user, RegisterUserDto.class);
        userRepository.save(user);
        return map;
    }

    @Override
    public UserEntity login(LoginUserDto registerUserDto) throws Exception {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(registerUserDto.getEmail(), registerUserDto.getPassword()));
        Optional<UserEntity> byEmail = userRepository.findByEmail(registerUserDto.getEmail());
        if (byEmail.isEmpty()) {
            throw new Exception();
        }
//        LoginUserDto map = modelMapper.map(byEmail, LoginUserDto.class);
//        return map;
        return byEmail.get();
    }
}
