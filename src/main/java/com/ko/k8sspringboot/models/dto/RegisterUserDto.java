package com.ko.k8sspringboot.models.dto;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class RegisterUserDto {
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private BigDecimal salary;
    private String password;

}
