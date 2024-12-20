package com.ko.k8sspringboot.service.impl;

import com.ko.k8sspringboot.repository.EmployeeRepository;
import com.ko.k8sspringboot.models.dto.EmployeeOldestDto;
import com.ko.k8sspringboot.models.entity.EmployeeEntity;
import com.ko.k8sspringboot.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeOldestDto getOldestEmployee() {
        Optional<EmployeeEntity> oldestEmployee = employeeRepository.findOldestEmployee();
        EmployeeEntity employeeEntity = oldestEmployee.orElseThrow(RuntimeException::new);

        return map(employeeEntity);
    }


    private static EmployeeOldestDto map(EmployeeEntity entity) {
        return new EmployeeOldestDto().setAge(entity.getAge())
                .setFirstName(entity.getFirstName())
                .setLastName(entity.getLastName());
    }
}
