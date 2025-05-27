package com.ko.k8sspringboot.controllers;

import com.ko.k8sspringboot.models.dto.EmployeeDto;
import com.ko.k8sspringboot.models.dto.EmployeeToProjectDto;
import com.ko.k8sspringboot.models.dto.ProjectCreateDto;
import com.ko.k8sspringboot.models.dto.ProjectInfoDto;
import com.ko.k8sspringboot.security.AuthenticationDetails;
import com.ko.k8sspringboot.service.EmployeeService;
import com.ko.k8sspringboot.service.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public List<ProjectInfoDto> getProjects(@AuthenticationPrincipal AuthenticationDetails authenticationDetails ) {
        List<ProjectInfoDto> all = projectService.getProjects();
        log.info("requested");
        return all;
    }

    @PostMapping
    public ProjectInfoDto create(@RequestBody ProjectCreateDto projectCreateDto) {
        ProjectInfoDto projectInfoDto = projectService.create(projectCreateDto);
        return projectInfoDto;
    }

    @GetMapping("{id}")
    public ProjectInfoDto getProject(@PathVariable Long id) {
        ProjectInfoDto project = projectService.getProject(id);
        return project;
    }

    @PostMapping("/{projectId}/employees/{employeeId}")
    public ResponseEntity addEmployeeToProject(@PathVariable Long projectId, @PathVariable UUID employeeId) {
        EmployeeToProjectDto employeeToProjectDto = new EmployeeToProjectDto();
        employeeToProjectDto.setEmployeeId(employeeId);
        employeeToProjectDto.setProjectId(projectId);
        projectService.addEmployeeToProject(employeeToProjectDto);
        return ResponseEntity.ok().build();
    }

}
