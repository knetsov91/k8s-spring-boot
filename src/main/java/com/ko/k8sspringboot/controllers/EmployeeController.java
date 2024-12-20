package com.ko.k8sspringboot.controllers;

import com.ko.k8sspringboot.models.dto.EmployeeOldestDto;
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
    private EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeService employeeService, EmployeeRepository employeeRepository) {
        this.employeeService = employeeService;
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/oldtest")
    public EmployeeOldestDto getOldestEmployee() {
        EmployeeOldestDto oldestEmployee = employeeService.getOldestEmployee();
        return oldestEmployee;

    }

    @GetMapping
    public List<EmployeeEntity> getEmployees() {
        List<EmployeeEntity> all = employeeRepository.findAll();
        return all;
    }
}
