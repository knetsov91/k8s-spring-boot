package com.ko.k8sspringboot.models.dto;

import com.ko.k8sspringboot.models.enums.ProjectState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProjectInfoDto {

    private String name;
    private String description;
    private ProjectState projectState;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate createdAt;

}
