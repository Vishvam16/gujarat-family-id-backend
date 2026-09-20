package org.placement.project.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "schemes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Scheme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false)
    private String category;

    private Double minimumIncome;

    private Double maximumIncome;

    private String ruralUrban;

    private String requiredCategory;

    @Builder.Default
    private Boolean active = true;
}