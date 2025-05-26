package com.ko.k8sspringboot.service.impl;

import com.ko.k8sspringboot.models.dto.EmployeeToProjectDto;
import com.ko.k8sspringboot.models.dto.ProjectCreateDto;
import com.ko.k8sspringboot.models.dto.ProjectInfoDto;
import com.ko.k8sspringboot.models.entity.EmployeeEntity;
import com.ko.k8sspringboot.models.entity.ProjectEntity;
import com.ko.k8sspringboot.models.enums.ProjectState;
import com.ko.k8sspringboot.repository.EmployeeRepository;
import com.ko.k8sspringboot.repository.ProjectRepository;
import com.ko.k8sspringboot.service.ProjectService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public ProjectServiceImpl(ProjectRepository projectRepository, EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.projectRepository = projectRepository;
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ProjectInfoDto> getProjects() {
       return projectRepository.findAll().stream().map(e -> modelMapper.map(e, ProjectInfoDto.class)).toList();
    }

    @Override
    public ProjectInfoDto create(ProjectCreateDto projectCreateDto) {
        ProjectEntity projectEntityMap = modelMapper.map(projectCreateDto, ProjectEntity.class);
        projectEntityMap.setCreatedAt(LocalDate.now());
        projectEntityMap.setProjectState(ProjectState.CREATED);
        ProjectEntity projectEntity = projectRepository.save(projectEntityMap);
        return modelMapper.map(projectEntity, ProjectInfoDto.class);
    }

    @Override
    public ProjectInfoDto getProject(Long id) {
        return projectRepository.findById(id)
                .map(p -> modelMapper.map(p, ProjectInfoDto.class))
                .orElseThrow(() -> new RuntimeException("Project with id " + id + " not found"));
    }

    @Transactional
    @Override
    public void addEmployeeToProject(EmployeeToProjectDto employeeToProjectDto) {
        UUID employeeId = employeeToProjectDto.getEmployeeId();
        Long projectId = employeeToProjectDto.getProjectId();

        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee with id " + employeeId + " not found"));
        ProjectEntity projectEntity = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project with id " + projectId + " not found"));

        projectEntity.getEmployees().add(employeeEntity);
        ProjectEntity save = projectRepository.save(projectEntity);


    }
}
