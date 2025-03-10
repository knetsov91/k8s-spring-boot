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
        EmployeeDto map = modelMapper.map(employeeEntity, EmployeeDto.class);
        return map;
    }

    @Override
    public List<EmployeeDto> getEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(e -> modelMapper.map(e, EmployeeDto.class))
                .toList();
    }

    @Override
    public EmployeeDto create(EmployeeDto employeeDto) {
        EmployeeEntity map = modelMapper.map(employeeDto, EmployeeEntity.class);
        EmployeeEntity save = employeeRepository.save(map);
        return modelMapper.map(save, EmployeeDto.class);
    }

    @Override
    public List<EmployeeDto> getAllEmployeesByProject(int projectId) {
        return List.of();


    }
}
