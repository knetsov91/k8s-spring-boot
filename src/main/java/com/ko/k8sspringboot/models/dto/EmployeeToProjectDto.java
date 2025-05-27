package com.ko.k8sspringboot.models.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class EmployeeToProjectDto {

    UUID employeeId;
    Long projectId;
}
