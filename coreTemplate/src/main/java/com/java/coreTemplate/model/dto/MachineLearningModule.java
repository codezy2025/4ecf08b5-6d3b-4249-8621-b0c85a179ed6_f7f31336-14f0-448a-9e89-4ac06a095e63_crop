package com.java.coreTemplate.model.dto;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "machine learning module")
@Getter @Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MachineLearningModule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "version_number", nullable = false, length = 50)
    private String versionNumber;

    @Column(name = "framework", nullable = false, length = 50)
    private String framework;

    @Column(name = "training_status", length = 50)
    private String trainingStatus;

    @Column(name = "last_trained_at")
    private java.time.LocalDateTime lastTrainedAt;

    @Column(name = "model_path", nullable = false, length = 500)
    private String modelPath;

    @Column(name = "created_at", updatable = false, nullable = false)
    private java.time.LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private java.time.LocalDateTime updatedAt;

    @Version
    private Long version;

    @PrePersist
    protected void onCreate() {
        createdAt = java.time.LocalDateTime.now();
        updatedAt = java.time.LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = java.time.LocalDateTime.now();
    }

    // Boolean getter with 'is' prefix as required
    public boolean isIsActive() {
        return isActive;
    }
}