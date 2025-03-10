package com.ko.k8sspringboot.models.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ProjectInfoDto {

    private String description;

    private String name;

    private LocalDate startDate;

    public String getDescription() {
        return description;
    }

    public ProjectInfoDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProjectInfoDto setName(String name) {
        this.name = name;
        return this;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public ProjectInfoDto setStartDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }
}
