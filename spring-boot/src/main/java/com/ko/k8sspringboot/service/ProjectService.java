package com.ko.k8sspringboot.service;

import com.ko.k8sspringboot.models.dto.EmployeeToProjectDto;
import com.ko.k8sspringboot.models.dto.ProjectCreateDto;
import com.ko.k8sspringboot.models.dto.ProjectInfoDto;
import com.ko.k8sspringboot.repository.ProjectRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;


public interface ProjectService {
    List<ProjectInfoDto> getProjects();
    ProjectInfoDto create(ProjectCreateDto projectCreateDto);
    ProjectInfoDto getProject(Long id);
    void addEmployeeToProject(EmployeeToProjectDto employeeToProjectDto);
}
