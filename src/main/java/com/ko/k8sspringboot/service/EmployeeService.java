package com.ko.k8sspringboot.service;

import com.ko.k8sspringboot.models.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    public EmployeeDto getOldestEmployee();
    public List<EmployeeDto> getEmployees();

    public List<EmployeeDto> getAllEmployeesByProject(int projectId);
}
