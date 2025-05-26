package com.ko.k8sspringboot.service;

import com.ko.k8sspringboot.models.dto.EmployeeDto;
import com.ko.k8sspringboot.models.entity.EmployeeEntity;

import java.util.List;

public interface EmployeeService {

     EmployeeDto getOldestEmployee();
     List<EmployeeDto> getEmployees();
     EmployeeDto create(EmployeeDto employeeDto);
     List<EmployeeDto> getAllEmployeesByProject(int projectId);
}
