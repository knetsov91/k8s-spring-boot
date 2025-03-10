package com.ko.k8sspringboot.models.entity;

import com.ko.k8sspringboot.models.enums.ProjectState;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.Set;

@Data
@Entity
@Table(name="projects")
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name="project_state", nullable = false)
    @Enumerated(EnumType.STRING)
    private ProjectState projectState;

    @Column(name="start_date", nullable = false)
    private LocalDate startDate;

    @Column(name="end_date")
    private LocalDate endDate;

    @ManyToMany(mappedBy = "projects")
    private Set<EmployeeEntity> employees;
}
