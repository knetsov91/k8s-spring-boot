package com.ko.k8sspringboot.service.impl;

import com.ko.k8sspringboot.models.dto.ProjectInfoDto;
import com.ko.k8sspringboot.models.entity.ProjectEntity;
import com.ko.k8sspringboot.repository.ProjectRepository;
import com.ko.k8sspringboot.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public List<ProjectInfoDto> getProjects() {
       return projectRepository.findAll().stream().map(e -> map(e)).toList();
    }

    private static ProjectInfoDto map(ProjectEntity projectEntity) {
        return new ProjectInfoDto().setDescription(projectEntity.getDescription())
                .setName(projectEntity.getName())
                .setStartDate(projectEntity.getStartDate());
    }
}
