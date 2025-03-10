package com.ko.k8sspringboot.controllers;

import com.ko.k8sspringboot.models.dto.EmployeeDto;
import com.ko.k8sspringboot.models.entity.EmployeeEntity;
import com.ko.k8sspringboot.repository.EmployeeRepository;
import com.ko.k8sspringboot.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;


    public EmployeeController(EmployeeService employeeService, ModelMapper modelMapper) {
        this.employeeService = employeeService;

    }

    @GetMapping("/oldtest")
    public EmployeeDto getOldestEmployee() {
        EmployeeDto oldestEmployee = employeeService.getOldestEmployee();
        return oldestEmployee;

    }

    @GetMapping
    public List<EmployeeDto> getEmployees() {
        List<EmployeeDto> all = employeeService.getEmployees();
        return all;
    }
    @GetMapping("/{projectId}")
    public List<EmployeeDto> getemployeesByProject(@PathVariable  int projectId) {
        return employeeService.getAllEmployeesByProject(projectId);


    }


}
