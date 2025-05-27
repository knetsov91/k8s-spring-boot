package com.ko.k8sspringboot.models.dto;

import com.ko.k8sspringboot.models.enums.ProjectState;
import lombok.Data;

@Data
public class ProjectCreateDto {

    private String name;
    private String description;
    private ProjectState projectState;

}
