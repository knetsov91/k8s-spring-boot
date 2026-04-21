package com.ko.k8sspringboot;

import com.ko.k8sspringboot.models.dto.EmployeeDto;
import com.ko.k8sspringboot.models.entity.EmployeeEntity;
import com.ko.k8sspringboot.repository.EmployeeRepository;
import com.ko.k8sspringboot.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

    @Test
    void getEmployees_returnsMappedDtos_whenEmployeesExist() {
        EmployeeEntity entity = EmployeeEntity.builder()
                .age(30).firstName("John").lastName("Doe")
                .salary(BigDecimal.valueOf(50000)).build();

        EmployeeDto dto = EmployeeDto.builder()
                .age(30).firstName("John").lastName("Doe")
                .salary(BigDecimal.valueOf(50000)).build();

        when(employeeRepository.findAll()).thenReturn(List.of(entity));
        when(modelMapper.map(entity, EmployeeDto.class)).thenReturn(dto);

        List<EmployeeDto> result = employeeService.getEmployees();

        assertEquals(1, result.size());
        assertEquals(dto, result.get(0));
        verify(modelMapper, times(1)).map(entity, EmployeeDto.class);
    }
}
