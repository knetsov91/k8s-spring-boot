package com.ko.k8sspringboot.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeeDto {

    private UUID id;
    private int age;
    private String firstName;
    private String lastName;
    private BigDecimal salary;

}
