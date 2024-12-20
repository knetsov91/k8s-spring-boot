package com.ko.k8sspringboot.models.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="projects")
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name="is_finised")
    private boolean isFinished;

    @Column(name="start_date")
    private LocalDate startDate;

    public Long getId() {
        return id;
    }

    public ProjectEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProjectEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProjectEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public ProjectEntity setFinished(boolean finished) {
        isFinished = finished;
        return this;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public ProjectEntity setStartDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }
}
