package com.ko.k8sspringboot.util;

import com.ko.k8sspringboot.models.dto.EmployeeDto;
import com.ko.k8sspringboot.models.entity.EmployeeEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Mapper {

   public static EmployeeDto mapEmployeeEntityToEmployeeDto(EmployeeEntity entity) {
       return EmployeeDto.builder()
               .age(entity.getAge())
               .firstName(entity.getFirstName())
               .lastName(entity.getLastName())
               .salary(entity.getSalary())
               .build();
   }


}
