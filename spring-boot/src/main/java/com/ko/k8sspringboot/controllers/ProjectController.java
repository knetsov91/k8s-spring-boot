package com.ko.k8sspringboot.controllers;

import com.ko.k8sspringboot.models.dto.EmployeeDto;
import com.ko.k8sspringboot.models.dto.ProjectInfoDto;
import com.ko.k8sspringboot.service.EmployeeService;
import com.ko.k8sspringboot.service.ProjectService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public List<ProjectInfoDto> getProjects() {
        List<ProjectInfoDto> all = projectService.getProjects();
        return all;
    }

}
