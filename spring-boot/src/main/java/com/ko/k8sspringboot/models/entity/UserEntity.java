package com.ko.k8sspringboot.models.entity;

import com.ko.k8sspringboot.models.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @CreationTimestamp
    @Column(updatable = false, name="created_at")
    private LocalDate createdAt;

    @UpdateTimestamp
    @Column(name="udated_at")
    private LocalDate updatedAt;

    @Column(nullable = false)
    private UserRole userRole;

    @Column(nullable = false)
    private boolean isActive;

    @OneToOne
    @JoinColumn(name="employee_id")
    private EmployeeEntity employee;
}
