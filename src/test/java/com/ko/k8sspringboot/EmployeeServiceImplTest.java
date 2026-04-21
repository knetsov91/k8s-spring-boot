package com.ko.k8sspringboot;

import com.ko.k8sspringboot.models.dto.EmployeeDto;
import com.ko.k8sspringboot.repository.EmployeeRepository;
import com.ko.k8sspringboot.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    void getEmployees_returnsEmptyList_whenNoEmployeesExist() {
        when(employeeRepository.findAll()).thenReturn(List.of());

        List<EmployeeDto> result = employeeService.getEmployees();

        assertTrue(result.isEmpty());
        verify(employeeRepository, times(1)).findAll();
        verifyNoInteractions(modelMapper);
    }
}
