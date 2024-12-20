package com.ko.k8sspringboot.controllers;

import com.ko.k8sspringboot.models.dto.EmployeeDto;
import com.ko.k8sspringboot.models.entity.EmployeeEntity;
import com.ko.k8sspringboot.repository.EmployeeRepository;
import com.ko.k8sspringboot.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;


    public EmployeeController(EmployeeService employeeService ) {
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

}
