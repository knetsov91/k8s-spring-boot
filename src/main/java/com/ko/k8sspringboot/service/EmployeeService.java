package com.ko.k8sspringboot.service;

import com.ko.k8sspringboot.models.dto.EmployeeOldestDto;
import com.ko.k8sspringboot.models.entity.EmployeeEntity;

import java.util.List;

public interface EmployeeService {

    public EmployeeOldestDto getOldestEmployee();
}
