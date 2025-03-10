package com.ko.k8sspringboot.service.impl;

import com.ko.k8sspringboot.repository.EmployeeRepository;
import com.ko.k8sspringboot.models.dto.EmployeeDto;
import com.ko.k8sspringboot.models.entity.EmployeeEntity;
import com.ko.k8sspringboot.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private ModelMapper modelMapper;
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public EmployeeDto getOldestEmployee() {
        Optional<EmployeeEntity> oldestEmployee = employeeRepository.findOldestEmployee();
        EmployeeEntity employeeEntity = oldestEmployee.orElseThrow(RuntimeException::new);

        return map(employeeEntity);
    }

    @Override
    public List<EmployeeDto> getEmployees() {
        return employeeRepository.findAll().stream().map(e -> map(e)).toList();
    }

    @Override
    public List<EmployeeDto> getAllEmployeesByProject(int projectId) {
        return employeeRepository.findByProject_Id(projectId)
                .stream()
                .map(employee -> modelMapper.map(employee, EmployeeDto.class))
                .toList();

    }


    private static EmployeeDto map(EmployeeEntity entity) {
        return new EmployeeDto().setAge(entity.getAge())
                .setFirstName(entity.getFirstName())
                .setLastName(entity.getLastName());
    }
}
