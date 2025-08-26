package com.java.coreTemplate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MachineLearningModuleRepository extends JpaRepository<MachineLearningModule, Long> {

    /**
     * Finds a machine learning module by its unique name (case-insensitive).
     * Uses native SQL query for better performance on large datasets.
     */
    @Query("SELECT m FROM MachineLearningModule m WHERE LOWER(m.name) = LOWER(:name)")
    Optional<MachineLearningModule> findByNameIgnoreCase(@Param("name") String name);

    /**
     * Finds all active machine learning modules (status = ACTIVE).
     */
    List<MachineLearningModule> findByStatusIgnoreCase(String status);

    /**
     * Finds modules by their creation date range (inclusive).
     */
    List<MachineLearningModule> findByCreatedDateBetween(
            @Param("startDate") java.time.LocalDateTime startDate,
            @Param("endDate") java.time.LocalDateTime endDate);

    /**
     * Finds modules by model type (e.g., "CNN", "LSTM") with partial matching.
     */
    List<MachineLearningModule> findByModelTypeContainingIgnoreCase(@Param("modelType") String modelType);

    /**
     * Finds modules by the owner's username (assuming a relationship with User entity).
     * Uses JOIN to query across related entities.
     */
    @Query("SELECT m FROM MachineLearningModule m JOIN m.owner o WHERE o.username = :username")
    List<MachineLearningModule> findByOwnerUsername(@Param("username") String username);

    /**
     * Checks if a module with the given name already exists (for uniqueness validation).
     */
    boolean existsByNameIgnoreCase(String name);

    /**
     * Counts the number of modules created in the last 30 days.
     */
    @Query("SELECT COUNT(m) FROM MachineLearningModule m WHERE m.createdDate >= :startDate")
    long countByCreatedDateAfter(@Param("startDate") java.time.LocalDateTime startDate);

    /**
     * Custom query to find modules with high accuracy (> 90%) and active status.
     * Demonstrates complex filtering using method name and @Query.
     */
    @Query("SELECT m FROM MachineLearningModule m " +
           "WHERE m.accuracy > :minAccuracy AND m.status = 'ACTIVE' " +
           "ORDER BY m.accuracy DESC")
    List<MachineLearningModule> findHighPerformingActiveModules(
            @Param("minAccuracy") Double minAccuracy);

    /**
     * Finds the most recently created module.
     */
    @Query("SELECT m FROM MachineLearningModule m ORDER BY m.createdDate DESC")
    List<MachineLearningModule> findTop10ByOrderByCreatedDateDesc();
}