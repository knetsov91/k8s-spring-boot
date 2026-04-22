package com.ko.k8sspringboot;

import com.ko.k8sspringboot.models.dto.EmployeeDto;
import com.ko.k8sspringboot.models.entity.EmployeeEntity;
import java.util.UUID;
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

    @Test
    void getEmployees_callsMapOncePerEntity_whenMultipleEmployeesExist() {
        EmployeeEntity entity1 = EmployeeEntity.builder()
                .age(30).firstName("John").lastName("Doe")
                .salary(BigDecimal.valueOf(50000)).build();
        EmployeeEntity entity2 = EmployeeEntity.builder()
                .age(25).firstName("Jane").lastName("Smith")
                .salary(BigDecimal.valueOf(60000)).build();

        EmployeeDto dto1 = EmployeeDto.builder().age(30).firstName("John").build();
        EmployeeDto dto2 = EmployeeDto.builder().age(25).firstName("Jane").build();

        when(employeeRepository.findAll()).thenReturn(List.of(entity1, entity2));
        when(modelMapper.map(entity1, EmployeeDto.class)).thenReturn(dto1);
        when(modelMapper.map(entity2, EmployeeDto.class)).thenReturn(dto2);

        List<EmployeeDto> result = employeeService.getEmployees();

        assertEquals(2, result.size());
        verify(modelMapper, times(1)).map(entity1, EmployeeDto.class);
        verify(modelMapper, times(1)).map(entity2, EmployeeDto.class);
        verifyNoMoreInteractions(modelMapper);
    }

    @Test
    void create_returnsMappedDto_whenEmployeeIsSaved() {
        EmployeeDto inputDto = EmployeeDto.builder()
                .age(30).firstName("John").lastName("Doe")
                .salary(BigDecimal.valueOf(50000)).build();

        EmployeeEntity mappedEntity = EmployeeEntity.builder()
                .age(30).firstName("John").lastName("Doe")
                .salary(BigDecimal.valueOf(50000)).build();

        EmployeeEntity savedEntity = EmployeeEntity.builder()
                .id(UUID.randomUUID())
                .age(30).firstName("John").lastName("Doe")
                .salary(BigDecimal.valueOf(50000)).build();

        EmployeeDto expectedDto = EmployeeDto.builder()
                .id(savedEntity.getId())
                .age(30).firstName("John").lastName("Doe")
                .salary(BigDecimal.valueOf(50000)).build();

        when(modelMapper.map(inputDto, EmployeeEntity.class)).thenReturn(mappedEntity);
        when(employeeRepository.save(mappedEntity)).thenReturn(savedEntity);
        when(modelMapper.map(savedEntity, EmployeeDto.class)).thenReturn(expectedDto);

        EmployeeDto result = employeeService.create(inputDto);

        assertEquals(expectedDto, result);
    }
}
