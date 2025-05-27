package com.ko.k8sspringboot.repository;

import com.ko.k8sspringboot.models.dto.EmployeeDto;
import com.ko.k8sspringboot.models.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, UUID> {

    @Query("SELECT e FROM EmployeeEntity as e  ORDER BY e.age DESC LIMIT 1")
    Optional<EmployeeEntity> findOldestEmployee();

//    List<EmployeeEntity> findByProject_Id(int projectId);
}
