package com.ko.k8sspringboot.service.impl;

import com.ko.k8sspringboot.models.dto.LoginUserDto;
import com.ko.k8sspringboot.models.dto.RegisterUserDto;
import com.ko.k8sspringboot.models.entity.EmployeeEntity;
import com.ko.k8sspringboot.models.entity.UserEntity;
import com.ko.k8sspringboot.models.enums.UserRole;
import com.ko.k8sspringboot.repository.EmployeeRepository;
import com.ko.k8sspringboot.repository.UserRepository;
import com.ko.k8sspringboot.service.AuthenticationService;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private ModelMapper modelMapper;

    public AuthenticationServiceImpl(UserRepository userRepository, EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.modelMapper = modelMapper;
    }

    @Override
    public RegisterUserDto register(RegisterUserDto registerUserDto) {
        UserEntity user = UserEntity.builder()
                            .email(registerUserDto.getEmail())
                            .password(passwordEncoder.encode(registerUserDto.getPassword()))
                            .userRole(UserRole.EMPLOYEE)
                            .createdAt(LocalDate.now())
                            .build();

        RegisterUserDto map = modelMapper.map(user, RegisterUserDto.class);

        EmployeeEntity employeeEntity = EmployeeEntity.builder()
                .age(registerUserDto.getAge())
                .salary(registerUserDto.getSalary())
                .hireDate(LocalDateTime.now())
                .firstName(registerUserDto.getFirstName())
                .lastName(registerUserDto.getLastName())
                .build();
        employeeRepository.save(employeeEntity);
        user.setEmployee(employeeEntity);
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

        return byEmail.get();
    }
}
