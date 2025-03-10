package com.ko.k8sspringboot.service.impl;

import com.ko.k8sspringboot.models.dto.ProjectCreateDto;
import com.ko.k8sspringboot.models.dto.ProjectInfoDto;
import com.ko.k8sspringboot.models.entity.ProjectEntity;
import com.ko.k8sspringboot.models.enums.ProjectState;
import com.ko.k8sspringboot.repository.ProjectRepository;
import com.ko.k8sspringboot.service.ProjectService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final ModelMapper modelMapper;

    public ProjectServiceImpl(ProjectRepository projectRepository, ModelMapper modelMapper) {
        this.projectRepository = projectRepository;
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
}
